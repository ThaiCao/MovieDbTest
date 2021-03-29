package com.app.moviedb.di.modules

import com.app.moviedb.features.detail.MovieDetailViewModel
import com.app.moviedb.features.home.MovieHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
}

val viewModelModule = module {
    viewModel { MovieHomeViewModel() }
    viewModel { MovieDetailViewModel() }
}