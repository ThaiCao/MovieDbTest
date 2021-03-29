package com.app.moviedb.core.model

import com.google.gson.annotations.SerializedName

class GenreModel(
@SerializedName("id")
val id: String,
@SerializedName("name")
val name: String?
) :BaseModel()