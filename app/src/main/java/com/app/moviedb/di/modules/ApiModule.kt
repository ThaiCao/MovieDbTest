package com.app.moviedb.di.modules

import com.app.moviedb.core.network.MovieApiDefinition
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(MovieApiDefinition::class.java) }
}