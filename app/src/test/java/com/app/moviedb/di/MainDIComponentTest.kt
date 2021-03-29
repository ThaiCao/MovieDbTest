package com.app.moviedb.di

import com.app.moviedb.di.modules.*

fun configureTestAppComponent(baseApi: String)
        = listOf(
    appModule,
    viewModelModule,
    apiModule,
    repoModule
)

