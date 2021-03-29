package com.app.moviedb.features.home.controller

import com.airbnb.epoxy.EpoxyModel
import com.app.moviedb.MovieHomeLargeBindingModel_
import com.app.moviedb.core.model.*
import com.app.moviedb.features.home.homeinterface.MovieClickListener

class MovieLargeListController(
    movieCategory: MovieCategory,
    clickListener: MovieClickListener? = null
): MovieCarouselController(movieCategory, clickListener) {
    override fun buildItemModel(currentPosition: Int, item: BaseModel?): EpoxyModel<*> {
        if(item is MovieModel){
            return item.run {
                MovieHomeLargeBindingModel_()
                    .id("${movieCategory}${this.id}")
                    .movieId(this.id)
                    .posterImage(this.getPosterUrl())
                    .clickListener(clickListener)
            } ?: run {
                MovieHomeLargeBindingModel_()
                    .id(-currentPosition)
            }
        }else{
            return item.run {
                MovieHomeLargeBindingModel_()
                    .id(-currentPosition)
            }
        }

    }
}