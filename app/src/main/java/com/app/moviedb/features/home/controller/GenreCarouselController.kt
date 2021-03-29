package com.app.moviedb.features.home.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieCategory
import com.app.moviedb.features.home.homeinterface.GenreClickListener
abstract class GenreCarouselController(
    protected val movieCategory: MovieCategory,
    protected val clickListener: GenreClickListener? = null
) : PagedListEpoxyController<BaseModel>() {

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)
    }
}