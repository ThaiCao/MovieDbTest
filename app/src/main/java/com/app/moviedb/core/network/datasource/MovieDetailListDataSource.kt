package com.app.moviedb.core.network.datasource

import androidx.paging.PageKeyedDataSource
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieDetailCategory
import com.app.moviedb.core.model.response.mapToAuthorModels
import com.app.moviedb.core.model.response.mapToMovieModels
import com.app.moviedb.core.network.MovieApiDefinition
import com.app.moviedb.core.network.utils.NetworkState
import io.reactivex.subjects.BehaviorSubject
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailListDataSource (
    private val movieId: String,
    private val category: MovieDetailCategory,
    private val initLoadState: BehaviorSubject<NetworkState>,
    private val loadMoreState: BehaviorSubject<NetworkState>
) : PageKeyedDataSource<Int, BaseModel>(), KoinComponent {

    private val api: MovieApiDefinition by inject()
    private var currentPage: Int = -1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BaseModel>
    ) {
        currentPage = 1
        if(category.key == MovieDetailCategory.COMMENT.key){
            api.fetchMovieReview(movieId)
                .doOnSubscribe { initLoadState.onNext(NetworkState.LOADING) }
                .doOnSuccess {
                    it.results?.run {
                        callback.onResult(
                            this.mapToAuthorModels(),
                            null,
                            null
                        )
                    }
                    initLoadState.onNext(NetworkState.IDLE)
                }
                .doOnError { initLoadState.onNext(NetworkState.ERROR) }
                .subscribe()
        }else{
            api.fetchMovieListDetail(movieId,category.key, currentPage)
                .doOnSubscribe { initLoadState.onNext(NetworkState.LOADING) }
                .doOnSuccess {
                    it.results?.run {
                        callback.onResult(
                            this.mapToMovieModels(),
                            null,
                            calculateNextPage(it.totalPages)
                        )
                    }
                    initLoadState.onNext(NetworkState.IDLE)
                }
                .doOnError { initLoadState.onNext(NetworkState.ERROR) }
                .subscribe()
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BaseModel>) {
        if (-1 == params.key || NetworkState.LOADING == loadMoreState.value) return
        if(category.key == MovieDetailCategory.COMMENT.key){
            api.fetchMovieReview(movieId)
                .doOnSubscribe { loadMoreState.onNext(NetworkState.LOADING) }
                .doOnSuccess {
                    it.results?.run {
                        callback.onResult(this.mapToAuthorModels(),null)
                    }
                    loadMoreState.onNext(NetworkState.IDLE)
                }
                .doOnError { loadMoreState.onNext(NetworkState.ERROR) }
                .subscribe()
        }else {
            api.fetchMovieListDetail(movieId, category.key)
                .doOnSubscribe { initLoadState.onNext(NetworkState.LOADING) }
                .doOnSuccess {
                    it.results?.run {
                        callback.onResult(
                            this.mapToMovieModels(),
                            null
                        )
                    }
                    initLoadState.onNext(NetworkState.IDLE)
                }
                .doOnError { initLoadState.onNext(NetworkState.ERROR) }
                .subscribe()
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BaseModel>) {
        // we don't need this
    }

    private fun calculateNextPage(totalPage: Int?): Int {
        totalPage?.run {
            currentPage = if (currentPage in 1 until totalPage) {
                currentPage.plus(1)
            } else {
                -1
            }
        }
        return currentPage
    }
}