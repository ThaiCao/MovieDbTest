package com.app.moviedb

import android.os.Bundle
import com.app.moviedb.core.ui.activity.BaseActivity
import com.app.moviedb.features.home.MovieHomeFragment
import com.app.moviedb.utils.openFragment

class MainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_main

    override fun handleErrorMessage(message: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(MovieHomeFragment(), false)
    }
}