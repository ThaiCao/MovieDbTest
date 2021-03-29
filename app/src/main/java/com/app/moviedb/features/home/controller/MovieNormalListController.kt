package com.app.moviedb.features.home.controller

import com.airbnb.epoxy.EpoxyModel
import com.app.moviedb.MovieHomeLargeBindingModel_
import com.app.moviedb.MovieHomeNormalBindingModel_
import com.app.moviedb.core.model.*
import com.app.moviedb.features.home.homeinterface.MovieClickListener

class MovieNormalListController(
    movieCategory: MovieCategory,
    clickListener: MovieClickListener? = null
): MovieCarouselController(movieCategory, clickListener) {
    override fun buildItemModel(currentPosition: Int, item: BaseModel?): EpoxyModel<*> {
        if(item is MovieModel){
            return item.run {
                MovieHomeNormalBindingModel_()
                    .id("${movieCategory}${this.id}")
                    .movieId(this.id)
                    .posterImage(this.getPosterUrl())
                    .title(this.displayTitle())
                    .clickListener(clickListener)
            } ?: run {
                MovieHomeNormalBindingModel_()
                    .id(-currentPosition)
            }
        }else{
            return MovieHomeNormalBindingModel_()
                .id(-currentPosition)
        }
    }
}