package com.app.moviedb.utils

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.moviedb.R

fun Activity.openFragment(
    fragment: Fragment,
    addToBackStack: Boolean
) {
    this as AppCompatActivity
    supportFragmentManager.beginTransaction().apply {
        add(R.id.fragmentContainer, fragment)
        if (addToBackStack) addToBackStack(fragment::class.java.simpleName)
        commit()
    }
}