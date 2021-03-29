package com.app.moviedb.core.model

import com.app.moviedb.BuildConfig
import com.app.moviedb.utils.format

const val PLACEHOLDER = "--"
class MovieModel(
    val id: String,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val title: String? = null,
    val voteAverage: Float? = null,
    val voteCount: Int? = null,
    val overview: String? = null,
    val releaseDate: String? = null,
    val genreList: List<GenreModel>? = null,
    val runtime: Int? = null
):BaseModel()

fun MovieModel.getPosterUrl(): String = "${BuildConfig.IMAGE_API_KEY}w500/${this.posterPath}"
fun MovieModel.getBackdropUrl(): String = "${BuildConfig.IMAGE_API_KEY}w500/${this.backdropPath}"
fun MovieModel.displayTitle(): String = this.title ?: PLACEHOLDER
fun MovieModel.display5StarsRating(): Float = this.voteAverage?.div(2) ?: 0.0f
fun MovieModel.displayVoteCount(): String = this.voteCount?.format() ?: PLACEHOLDER
fun MovieModel.displayReleaseDate(): String = this.releaseDate ?: PLACEHOLDER
fun MovieModel.displayOverview(): String = this.overview ?: PLACEHOLDER