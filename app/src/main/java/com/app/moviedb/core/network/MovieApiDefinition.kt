package com.app.moviedb.core.network

import com.app.moviedb.core.model.response.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiDefinition {
    @GET("movie/{list}")
    fun fetchMovieList(
        @Path("list") list: String,
        @Query("page") page: Int? = null
    ): Single<TmdbApiResponse<MovieListResponse>>

    @GET("genre/movie/list")
    fun fetchGenreList(): Single<GenreApiResponse<GenreListResponse>>

    @GET("trending/movie/week")
    fun fetchTrendingList(): Single<TmdbApiResponse<MovieListResponse>>

    @GET("movie/{movieId}")
    fun fetchMovieDetail(@Path("movieId") movieId: String): Single<MovieDetailResponse>

    @GET("movie/{movieId}/reviews")
    fun fetchMovieReview(@Path("movieId") movieId: String): Single<TmdbApiResponse<AuthorReponse>>

//    @GET("movie/{movieId}/similar")
//    fun fetchMovieSimilar(@Path("movieId") movieId: String,   @Query("page") page: Int? = null): Single<TmdbApiResponse<MovieListResponse>>
//
//    @GET("movie/{movieId}/recommendations")
//    fun fetchMovieRecommendations(@Path("movieId") movieId: String,   @Query("page") page: Int? = null): Single<TmdbApiResponse<MovieListResponse>>

    @GET("movie/{movieId}/{list}")
    fun fetchMovieListDetail(@Path("movieId") movieId: String, @Path("list") list: String,   @Query("page") page: Int? = null): Single<TmdbApiResponse<MovieListResponse>>
}