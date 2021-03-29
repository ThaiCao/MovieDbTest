package com.app.moviedb.features.home.controller

import android.util.Log
import com.airbnb.epoxy.EpoxyController
import com.app.moviedb.R
import com.app.moviedb.core.model.MovieCategory
import com.app.moviedb.core.network.utils.NetworkState
import com.app.moviedb.features.home.MovieCategoryListing
import com.app.moviedb.features.home.models.MovieCarouselModel_
import com.app.moviedb.features.home.view.CategoryHeaderHolder_
import com.app.moviedb.features.home.view.HomeLoadInitView_
import com.app.moviedb.features.home.view.HomeSeparatorView_

class MovieHomeController : EpoxyController(){
    var categoryListings: Map<MovieCategory, MovieCategoryListing>? = null
    override fun buildModels() {
        categoryListings?.forEach { category, listing ->
            Log.e("TEST_DATA","category= ${category.name}")
            CategoryHeaderHolder_()
                .id("${category.key}-header")
                .category(category)
                .clickListener(listing.headerClickListener)
                .addTo(this)
            if (listing.loadingState == NetworkState.LOADING) {
                HomeLoadInitView_()
                    .id("${category.key}-loading")
                    .addTo(this)
            } else {
                MovieCarouselModel_()
                    .id("${category.key}-list")
                    .models(emptyList()) // this is required
                    .epoxyController(listing.carouselController)
                    .paddingRes(R.dimen.size_8)
                    .numViewsToShowOnScreen(listing.itemCountOnScreen)
                    .addTo(this)
            }
//            HomeSeparatorView_()
//                .id("${category.key}-separator")
//                .addTo(this)
        }
    }
}