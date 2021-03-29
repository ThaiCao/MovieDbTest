package com.app.moviedb.core.network.datasource

import androidx.paging.DataSource
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieDetailCategory
import com.app.moviedb.core.network.utils.NetworkState
import io.reactivex.subjects.BehaviorSubject

class MovieDetailListDataSourceFactory (private val category: MovieDetailCategory, private val movieId: String) :
    DataSource.Factory<Int, BaseModel>() {

    val initLoadState = BehaviorSubject.createDefault(NetworkState.IDLE)
    val loadMoreState = BehaviorSubject.createDefault(NetworkState.IDLE)
    var dataSource: MovieDetailListDataSource? = null

    override fun create(): DataSource<Int, BaseModel> {
        dataSource = MovieDetailListDataSource(movieId, category, initLoadState, loadMoreState)
        return dataSource!!
    }

}