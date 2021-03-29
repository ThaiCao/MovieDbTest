package com.app.moviedb.di.utils

import com.app.moviedb.core.network.utils.RxErrorHandler
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val gsonModule = module {
    single { Gson() }
}

val errorHandleModule = module {
    single(createdAtStart = true) { RxErrorHandler(androidApplication()) }
}