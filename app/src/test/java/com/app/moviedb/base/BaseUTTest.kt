package com.app.moviedb.base

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.File

abstract class BaseUTTest : KoinTest {
    /**
     * For MockWebServer instance
     */
    private lateinit var mMockServerInstance: MockWebServer

    /**
     * Default, let server be shut down
     */
    private var mShouldStart = false

    @Before
    open fun setUp(){
        startMockServer(true)
    }

    @After
    open fun tearDown(){
        //Stop Mock server
        stopMockServer()
        //Stop Koin as well
        stopKoin()
    }

    /**
     * Start Mockwebserver
     */
    private fun startMockServer(shouldStart:Boolean){
        if (shouldStart){
            mShouldStart = shouldStart
            mMockServerInstance = MockWebServer()
            mMockServerInstance.start()
        }
    }

    /**
     * Set Mockwebserver url
     */
    fun getMockWebServerUrl() = mMockServerInstance.url("/").toString()

    /**
     * Stop Mockwebserver
     */
    private fun stopMockServer() {
        if (mShouldStart){
            mMockServerInstance.shutdown()
        }
    }

}