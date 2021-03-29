package com.app.moviedb.features.detail

import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.network.utils.NetworkState
import com.app.moviedb.features.detail.view.CategoryDetailHeaderHolder

data class MovieDetailCategoryListing(
    val headerClickListener: CategoryDetailHeaderHolder.OnHeaderClickListener? = null,
    var loadingState: NetworkState? = null,
    val carouselController: PagedListEpoxyController<BaseModel>,
    val itemCountOnScreen: Float = 0.0f
)
