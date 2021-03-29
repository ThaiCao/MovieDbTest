package com.app.moviedb

import android.app.Application
import com.app.moviedb.di.modules.*
import com.app.moviedb.di.utils.errorHandleModule
import com.app.moviedb.di.utils.gsonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        dependenciesInjection()
    }


    private fun dependenciesInjection() {
        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(this@BaseApplication)
            val dependencies =
                mutableListOf(gsonModule, errorHandleModule, networkModule).apply {
                    addAll(defineDependencies())
                }
            modules(dependencies)
        }
    }

    fun defineDependencies() = listOf(
        appModule,
        viewModelModule,
        apiModule,
        repoModule
    )
}