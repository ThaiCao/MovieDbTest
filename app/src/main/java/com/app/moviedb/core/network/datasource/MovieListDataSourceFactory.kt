package com.app.moviedb.core.network.datasource

import androidx.paging.DataSource
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieCategory
import com.app.moviedb.core.network.utils.NetworkState
import io.reactivex.subjects.BehaviorSubject

class MovieListDataSourceFactory (private val category: MovieCategory) :
    DataSource.Factory<Int, BaseModel>() {

    val initLoadState = BehaviorSubject.createDefault(NetworkState.IDLE)
    val loadMoreState = BehaviorSubject.createDefault(NetworkState.IDLE)
    var dataSource: MovieListDataSource? = null

    override fun create(): DataSource<Int, BaseModel> {
        dataSource = MovieListDataSource(category, initLoadState, loadMoreState)
        return dataSource!!
    }

}