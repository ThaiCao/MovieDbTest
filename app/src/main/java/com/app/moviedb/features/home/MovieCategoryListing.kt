package com.app.moviedb.features.home

import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.network.utils.NetworkState
import com.app.moviedb.features.home.view.CategoryHeaderHolder

data class MovieCategoryListing(
    val headerClickListener: CategoryHeaderHolder.OnHeaderClickListener? = null,
    var loadingState: NetworkState? = null,
    val carouselController: PagedListEpoxyController<BaseModel>,
    val itemCountOnScreen: Float = 0.0f
)
