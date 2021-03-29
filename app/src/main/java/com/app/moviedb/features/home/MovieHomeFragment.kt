package com.app.moviedb.features.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.app.moviedb.R
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieCategory
import com.app.moviedb.core.model.MovieModel
import com.app.moviedb.core.network.utils.NetworkState
import com.app.moviedb.core.ui.fragment.BaseFragment
import com.app.moviedb.features.detail.MovieDetailFragment
import com.app.moviedb.features.home.controller.*
import com.app.moviedb.features.home.homeinterface.GenreClickListener
import com.app.moviedb.features.home.homeinterface.MovieClickListener
import com.app.moviedb.features.home.view.CategoryHeaderHolder
import com.app.moviedb.utils.openFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieHomeFragment : BaseFragment(), CategoryHeaderHolder.OnHeaderClickListener,
    MovieClickListener, GenreClickListener {

    private val movieViewModel: MovieHomeViewModel by viewModel()
    override fun getLayoutId() = R.layout.fragment_movie_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryListings = mutableMapOf<MovieCategory, MovieCategoryListing>()
        val homeController = MovieHomeController()
        MovieCategory.values().forEachIndexed { index, category ->
            val carouselController: PagedListEpoxyController<BaseModel>
            val itemsOnScreen: Float
            // first category uses large carousel, other uses normal carousel
            if(category.key == MovieCategory.TRENDING.key){
                carouselController = MovieLargeListController(category, this)
                itemsOnScreen = 1.1f
            }else if(category.key == MovieCategory.GENRE.key){
                carouselController = GenreListController(category, this)
                itemsOnScreen = 2.2f
            }else{
                carouselController = MovieNormalListController(category, this)
                itemsOnScreen = 2.2f
            }
            categoryListings[category] =
                MovieCategoryListing(
                    this,
                    NetworkState.LOADING,
                    carouselController,
                    itemsOnScreen
                )

//            val listing = movieViewModel.getList(category)
            val listing = movieViewModel.fetchList(category)
            listing.pagedList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { carouselController.submitList(it) }
                .subscribe()
                .disposeOnDestroy()
            listing.refreshState
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnNext {
                    categoryListings[category]?.loadingState = it
                    homeController.requestModelBuild()
                }?.subscribe()
                ?.disposeOnDestroy()
            listing.loadMoreState
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnNext {
                    if(carouselController is MovieCarouselController){
                        carouselController.loadingMore =
                            (it == NetworkState.LOADING)
                    }

                }?.subscribe()
                ?.disposeOnDestroy()
        }
        homeController.categoryListings = categoryListings

        with(rvHome) {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            setControllerAndBuildModels(homeController)
        }

        with(swipeRefreshHome) {
            movieViewModel.refreshState()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { this.isRefreshing = (it == NetworkState.LOADING) }
                .subscribe()
                .disposeOnDestroy()

            setOnRefreshListener {
                movieViewModel.refresh()
            }
        }

    }

    override fun onViewAllClicked(category: MovieCategory) {
        Toast.makeText(context, R.string.view_all_click, Toast.LENGTH_SHORT).show()
    }

    override fun onMovieClicked(movieId: String) {
        activity?.openFragment(MovieDetailFragment.newInstance(movieId), true)
    }

    override fun onStart() {
        super.onStart()
        movieViewModel.refresh()
    }

    override fun onGenreClicked(genreId: String) {
        Toast.makeText(context, R.string.genre_click, Toast.LENGTH_SHORT).show()
    }
}