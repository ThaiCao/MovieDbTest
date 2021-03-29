package com.app.moviedb.core.model.response

import com.google.gson.annotations.SerializedName

class GenreApiResponse<T> {
    @SerializedName("genres")
    val genres: List<GenreListResponse>? = null
}