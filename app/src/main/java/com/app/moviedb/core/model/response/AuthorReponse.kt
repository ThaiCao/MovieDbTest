package com.app.moviedb.core.model.response

import com.app.moviedb.core.model.AuthorDetails
import com.app.moviedb.core.model.AuthorModel
import com.google.gson.annotations.SerializedName

class AuthorReponse (
    @SerializedName("author")
    val author: String,
    @SerializedName("author_details")
    val authorDetails: AuthorDetails,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
)
fun List<AuthorReponse>.mapToAuthorModels(): List<AuthorModel> =
    this.map {
        AuthorModel(
            author = it.author,
            authorDetails = it.authorDetails,
            content = it.content,
            createdAt = it.createdAt,
            id = it.id,
            updatedAt = it.updatedAt,
            url = it.url
        )
    }