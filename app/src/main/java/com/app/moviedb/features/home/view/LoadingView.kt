package com.app.moviedb.features.home.view

import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.app.moviedb.R

@EpoxyModelClass(layout = R.layout.view_home_load_init)
abstract class HomeLoadInitView : EpoxyModel<View>()

@EpoxyModelClass(layout = R.layout.view_home_load_more)
abstract class HomeLoadMoreView : EpoxyModel<View>()