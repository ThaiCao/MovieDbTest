package com.app.moviedb.core.model

import androidx.annotation.StringRes
import com.app.moviedb.R

enum class MovieCategory (val key: String, @StringRes val strRes: Int) {
    TRENDING("trending", R.string.category_trending),
    GENRE("genre", R.string.category_genre),
    POPULAR("popular", R.string.category_popular),
    TOP_RATED("top_rated", R.string.category_top_rated),
    UPCOMING("upcoming", R.string.category_upcoming);
}