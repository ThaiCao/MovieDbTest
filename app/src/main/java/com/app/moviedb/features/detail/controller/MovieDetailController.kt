package com.app.moviedb.features.detail.controller

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.app.moviedb.R
import com.app.moviedb.core.model.AuthorModel
import com.app.moviedb.core.model.MovieDetailCategory
import com.app.moviedb.core.network.utils.NetworkState
import com.app.moviedb.features.detail.MovieDetailCategoryListing
import com.app.moviedb.features.detail.models.CommentEpoxyRecyclerViewModel_
import com.app.moviedb.features.detail.view.CategoryDetailHeaderHolder_
import com.app.moviedb.features.home.models.MovieCarouselModel_
import com.app.moviedb.features.home.view.HomeLoadInitView_

class MovieDetailController(private val layoutManager: RecyclerView.LayoutManager) : EpoxyController(){
    var categoryListings: Map<MovieDetailCategory, MovieDetailCategoryListing>? = null
    override fun buildModels() {
        categoryListings?.forEach { category, listing ->
            Log.e("TEST_DATA","category= ${category.name}")
            CategoryDetailHeaderHolder_()
                .id("${category.key}-header")
                .category(category)
                .clickListener(listing.headerClickListener)
                .addTo(this)
            if (listing.loadingState == NetworkState.LOADING) {
                HomeLoadInitView_()
                    .id("${category.key}-loading")
                    .addTo(this)
            } else {
                if(category.key == MovieDetailCategory.COMMENT.key){
                    CommentEpoxyRecyclerViewModel_()
                        .id("${category.key}-list")
                        .models(emptyList()) // this is required
                        .epoxyController(listing.carouselController)
                        .layoutManager(layoutManager)
//                        .numViewsToShowOnScreen(listing.itemCountOnScreen)
                        .addTo(this)
                }else{
                    MovieCarouselModel_()
                        .id("${category.key}-list")
                        .models(emptyList()) // this is required
                        .epoxyController(listing.carouselController)
                        .paddingRes(R.dimen.size_8)
                        .numViewsToShowOnScreen(listing.itemCountOnScreen)
                        .addTo(this)
                }
            }
        }
    }
}