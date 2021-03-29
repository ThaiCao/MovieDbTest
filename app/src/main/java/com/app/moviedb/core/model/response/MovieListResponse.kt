package com.app.moviedb.core.model.response

import com.app.moviedb.core.model.MovieModel
import com.google.gson.annotations.SerializedName

class MovieListResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("release_date")
    val releaseDate: String?
)

fun List<MovieListResponse>.mapToMovieModels(): List<MovieModel> =
    this.map {
        MovieModel(
            id = it.id,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            title = it.title,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            releaseDate = it.releaseDate
        )
    }