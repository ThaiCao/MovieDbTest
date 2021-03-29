package com.app.moviedb.di.modules

import com.app.moviedb.core.repository.AppRepository
import com.app.moviedb.core.repository.AppRepositoryImpl
import org.koin.dsl.module

//package com.app.moviedb.di.modules


val repoModule = module {
    single<AppRepository> { AppRepositoryImpl() }
//    single<ApiRepository> { ApiRepositoryImpl() }
}
