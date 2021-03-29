package com.app.moviedb.core.model

import com.app.moviedb.BuildConfig
import com.google.gson.annotations.SerializedName

class AuthorModel (
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
) :BaseModel()


fun AuthorModel.getAvatarUrl(): String = "${BuildConfig.IMAGE_API_KEY}w500/${this.authorDetails.avatarPath}"
fun AuthorModel.displayName(): String = this.authorDetails.username ?: PLACEHOLDER
fun AuthorModel.display5StarsRating(): Float = this.authorDetails.rating?.div(2) ?: 0.0f