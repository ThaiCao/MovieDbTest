package com.app.moviedb.repository

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.moviedb.R
import com.app.moviedb.base.BaseUTTest
import com.app.moviedb.core.model.MovieCategory
import com.app.moviedb.core.network.MovieApiDefinition
import com.app.moviedb.core.repository.ApiRepository
import com.app.moviedb.core.repository.ApiRepositoryImpl
import com.app.moviedb.core.repository.AppRepository
import com.app.moviedb.core.repository.AppRepositoryImpl
import com.app.moviedb.di.configureTestAppComponent
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject

@RunWith(JUnit4::class)
class ApiRepositoryTest : BaseUTTest(){
    //Target
    private lateinit var mRepo: ApiRepository

    //Inject api service created with koin
    val mAPIService : MovieApiDefinition by inject()

    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start(){
        super.setUp()

        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_list_trending_expected_data() =  runBlocking<Unit>{
        mRepo = ApiRepositoryImpl(AppRepositoryImpl())

        val category= MovieCategory.values()[0]
        val dataReceived = mRepo.fetchMovieList(category)

        Assert.assertNotNull(dataReceived)
    }

    @Test
    fun test_list_genre_expected_data() =  runBlocking<Unit>{
        mRepo = ApiRepositoryImpl(AppRepositoryImpl())

        val category= MovieCategory.values()[1]
        val dataReceived = mRepo.fetchMovieList(category)

        Assert.assertNotNull(dataReceived)
    }

    @Test
    fun test_list_popular_expected_data() =  runBlocking<Unit>{
        mRepo = ApiRepositoryImpl(AppRepositoryImpl())

        val category= MovieCategory.values()[2]
        val dataReceived = mRepo.fetchMovieList(category)

        Assert.assertNotNull(dataReceived)
    }

    @Test
    fun test_list_top_rated_expected_data() =  runBlocking<Unit>{
        mRepo = ApiRepositoryImpl(AppRepositoryImpl())

        val category= MovieCategory.values()[3]
        val dataReceived = mRepo.fetchMovieList(category)

        Assert.assertNotNull(dataReceived)
    }

    @Test
    fun test_list_upcoming_expected_data() =  runBlocking<Unit>{
        mRepo = ApiRepositoryImpl(AppRepositoryImpl())

        val category= MovieCategory.values()[4]
        val dataReceived = mRepo.fetchMovieList(category)

        Assert.assertNotNull(dataReceived)
    }

}