package com.app.moviedb.core.model

import com.google.gson.annotations.SerializedName

class AuthorDetails (
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar_path")
    val avatarPath: String,
    @SerializedName("rating")
    val rating: Float
)