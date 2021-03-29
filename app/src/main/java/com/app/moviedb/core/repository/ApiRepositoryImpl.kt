package com.app.moviedb.core.repository

import androidx.annotation.VisibleForTesting
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieCategory
import com.app.moviedb.core.model.MovieDetailCategory
import com.app.moviedb.core.model.MovieModel
import com.app.moviedb.core.network.MovieApiDefinition
import com.app.moviedb.core.network.datasource.MovieDetailListDataSourceFactory
import com.app.moviedb.core.network.datasource.MovieListDataSourceFactory
import com.app.moviedb.core.network.utils.IncrementalNextPage
import com.app.moviedb.core.network.utils.Listing
import com.app.moviedb.core.network.utils.NextPageIndex
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import javax.inject.Inject

class ApiRepositoryImpl  @Inject constructor(
    private val appRepository: AppRepository
) : ApiRepository, KoinComponent {

    private val movieApi: MovieApiDefinition by inject()
    private val nextPageIndex: NextPageIndex by lazy { IncrementalNextPage() }

    companion object {
        val TAG = ApiRepositoryImpl::class.simpleName

        @Volatile
        private var INSTANCE: ApiRepositoryImpl? = null

        fun getInstance(appRepository: AppRepository) =
            INSTANCE ?: synchronized(ApiRepositoryImpl::class.java) {
                INSTANCE ?: ApiRepositoryImpl(
                    appRepository
                ).also {
                    INSTANCE = it
                }
            }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun fetchMovieList(category: MovieCategory, pageSize: Int): Listing<BaseModel> {
        val dataSourceFactory = MovieListDataSourceFactory(category)
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(DEFAULT_PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        val pagedList = RxPagedListBuilder(dataSourceFactory, pagedListConfig)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
        return Listing(
            pagedList = pagedList,
            refreshState = dataSourceFactory.initLoadState,
            loadMoreState = dataSourceFactory.loadMoreState,
            refresh = { dataSourceFactory.dataSource?.invalidate() }
        )
    }

    override fun fetchMovieDetail(movieId: String): Single<MovieModel> {
        return movieApi.fetchMovieDetail(movieId)
            .map { response ->
                MovieModel(
                    id = response.id,
                    posterPath = response.posterPath,
                    backdropPath = response.backdropPath,
                    title = response.title,
                    voteAverage = response.voteAverage,
                    voteCount = response.voteCount,
                    overview = response.overview,
                    releaseDate = response.releaseDate,
                    genreList = response.genreList,
                    runtime = response.runtime
                )
            }
    }

    override fun fetchMovieDetailList(
        movieId: String,
        category: MovieDetailCategory,
        pageSize: Int
    ): Listing<BaseModel> {
        val dataSourceFactory = MovieDetailListDataSourceFactory(category, movieId)
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(DEFAULT_PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        val pagedList = RxPagedListBuilder(dataSourceFactory, pagedListConfig)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
        return Listing(
            pagedList = pagedList,
            refreshState = dataSourceFactory.initLoadState,
            loadMoreState = dataSourceFactory.loadMoreState,
            refresh = { dataSourceFactory.dataSource?.invalidate() }
        )
    }
}