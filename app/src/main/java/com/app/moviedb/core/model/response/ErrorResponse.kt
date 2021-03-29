package com.app.moviedb.core.model.response

import androidx.annotation.Keep

@Keep
data class ErrorResponse(
    val message: String
)