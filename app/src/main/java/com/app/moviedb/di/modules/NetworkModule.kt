package com.app.moviedb.di.modules

import com.app.moviedb.BuildConfig
import com.app.moviedb.core.network.utils.ApiInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single { ApiInterceptor() }
    single {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(get<HttpLoggingInterceptor>())
        builder.addInterceptor(get<ApiInterceptor>())
        builder.protocols(listOf(Protocol.HTTP_1_1, Protocol.HTTP_2))
        builder.build()
    }

    single<Converter.Factory> { GsonConverterFactory.create() }
    single<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_BASE_URL)
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .client(get())
            .build()
    }
}