package com.app.moviedb.core.model.response

import com.google.gson.annotations.SerializedName

class TmdbApiResponse<T>(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("results")
    val results: List<T>? = null
)