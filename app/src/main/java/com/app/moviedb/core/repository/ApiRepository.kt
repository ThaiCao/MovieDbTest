package com.app.moviedb.core.repository

import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieCategory
import com.app.moviedb.core.model.MovieDetailCategory
import com.app.moviedb.core.model.MovieModel
import com.app.moviedb.core.network.utils.Listing
import io.reactivex.Single

const val DEFAULT_PAGE_SIZE = 5
interface ApiRepository {

    /**
     * fetch movie list base on category
     */
    fun fetchMovieList(
        category: MovieCategory,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): Listing<BaseModel>

    /**
     * fetch movie list detail by movieId
     */
    fun fetchMovieDetail(movieId: String): Single<MovieModel>

    /**
     * fetch movie list of detail page by movieId
     */
    fun fetchMovieDetailList(
        movieId: String,
        category: MovieDetailCategory,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): Listing<BaseModel>
}