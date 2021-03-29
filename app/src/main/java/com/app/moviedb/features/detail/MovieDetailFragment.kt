package com.app.moviedb.features.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.app.moviedb.R
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieDetailCategory
import com.app.moviedb.core.network.utils.NetworkState
import com.app.moviedb.core.ui.view.BaseBindingFragment
import com.app.moviedb.databinding.FragmentMovieDetailBinding
import com.app.moviedb.features.detail.controller.MovieDetailCommentController
import com.app.moviedb.features.detail.controller.MovieDetailController
import com.app.moviedb.features.detail.controller.MovieDetailRedcomandListController
import com.app.moviedb.features.detail.controller.MovieDetailVideoListController
import com.app.moviedb.features.detail.view.CategoryDetailHeaderHolder
import com.app.moviedb.features.home.controller.MovieCarouselController
import com.app.moviedb.features.home.homeinterface.MovieClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseBindingFragment<FragmentMovieDetailBinding>(),
    CategoryDetailHeaderHolder.OnHeaderClickListener, MovieClickListener {

    private val movieViewModel: MovieDetailViewModel by viewModel()

    override fun getLayoutId() = R.layout.fragment_movie_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = movieViewModel
        movieViewModel.fetchMovieDetail(arguments?.getString(FIELD_MOVIE_ID) ?: "")
        buttonBack.setOnClickListener { activity?.onBackPressed() }

        val categoryListings = mutableMapOf<MovieDetailCategory, MovieDetailCategoryListing>()
        val movieDetailController = MovieDetailController(layoutManager =  LinearLayoutManager(this.context, RecyclerView.VERTICAL, false))
        var itemsOnScreen: Float
        MovieDetailCategory.values().forEachIndexed { index, movieDetailCategory ->
            val carouselController: PagedListEpoxyController<BaseModel>
            if(movieDetailCategory.key == MovieDetailCategory.COMMENT.key){
                carouselController = MovieDetailCommentController(movieDetailCategory, this)
                itemsOnScreen = 1f
            }else if(movieDetailCategory.key == MovieDetailCategory.VIDEO.key){
                carouselController = MovieDetailVideoListController(movieDetailCategory, this)
                itemsOnScreen = 1.6f
            }else{
                carouselController = MovieDetailRedcomandListController(movieDetailCategory, this)
                itemsOnScreen = 3f
            }

            categoryListings[movieDetailCategory] =
                MovieDetailCategoryListing(
                    this,
                    NetworkState.LOADING,
                    carouselController,
                    itemsOnScreen
                )
            val listing = movieViewModel.fetchList(arguments?.getString(FIELD_MOVIE_ID) ?: "", movieDetailCategory)

            listing.pagedList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { carouselController.submitList(it) }
                .subscribe()
                .disposeOnDestroy()
            listing.refreshState
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnNext {
                    categoryListings[movieDetailCategory]?.loadingState = it
                    movieDetailController.requestModelBuild()
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
            movieDetailController.categoryListings = categoryListings

            with(rvDetail) {
                layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
                setControllerAndBuildModels(movieDetailController)
            }
            movieViewModel.refresh()
        }
    }

    companion object {
        const val FIELD_MOVIE_ID = "movieId"

        fun newInstance(movieId: String): MovieDetailFragment {
            return MovieDetailFragment().apply {
                arguments = bundleOf(FIELD_MOVIE_ID to movieId)
            }
        }
    }

    override fun onViewAllClicked(category: MovieDetailCategory) {
        Toast.makeText(context, R.string.view_all_click, Toast.LENGTH_SHORT).show()
    }

    override fun onMovieClicked(movieId: String) {

    }
}