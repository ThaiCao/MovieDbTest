package com.app.moviedb.core.model

import androidx.annotation.StringRes
import com.app.moviedb.R

enum class MovieDetailCategory(val key: String, @StringRes val strRes: Int) {
    VIDEO("similar", R.string.category_video),
    COMMENT("reviews", R.string.category_comments),
    RECOMENDATIONS("recommendations", R.string.category_recomendations);
}