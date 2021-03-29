package com.app.moviedb.core.model.response

import com.app.moviedb.core.model.GenreModel
import com.google.gson.annotations.SerializedName

class GenreListResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?)

fun List<GenreListResponse>.mapToGenreModels(): List<GenreModel> =
    this.map {
        GenreModel(
            id = it.id,
            name = it.name
        )
    }