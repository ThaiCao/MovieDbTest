package com.app.moviedb.core.repository

import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
): AppRepository{

    override val apiRepository = ApiRepositoryImpl.getInstance(this)

}