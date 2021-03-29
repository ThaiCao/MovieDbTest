package com.app.moviedb.features.detail.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieDetailCategory
import com.app.moviedb.features.home.homeinterface.MovieClickListener
import com.app.moviedb.features.home.view.HomeLoadMoreView
import com.app.moviedb.features.home.view.HomeLoadMoreView_

abstract class MovieDetailCarouselController (
    protected val movieCategory: MovieDetailCategory,
    protected val clickListener: MovieClickListener? = null
) : PagedListEpoxyController<BaseModel>() {

    var loadingMore = false
        set(value) {
            field = value
            requestModelBuild()
        }

    private val loadingMoreView =
        HomeLoadMoreView_().apply { id(HomeLoadMoreView::class.java.simpleName) }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)
        loadingMoreView.addIf(loadingMore && models.isNotEmpty(), this)
    }
}