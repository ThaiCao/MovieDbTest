package com.app.moviedb.features.detail.controller

import com.airbnb.epoxy.EpoxyModel
import com.app.moviedb.MovieDetailRedcomandBindingModel_
import com.app.moviedb.core.model.*
import com.app.moviedb.features.home.homeinterface.MovieClickListener

class MovieDetailRedcomandListController (
    movieCategory: MovieDetailCategory,
    clickListener: MovieClickListener? = null
): MovieDetailCarouselController(movieCategory, clickListener){
    override fun buildItemModel(currentPosition: Int, item: BaseModel?): EpoxyModel<*> {
        if(item is MovieModel){
            return item.run {
                MovieDetailRedcomandBindingModel_()
                    .id("${movieCategory}${this.id}")
                    .movieId(this.id)
                    .posterImage(this.getPosterUrl())
                    .title(this.displayTitle())
                    .clickListener(clickListener)
            } ?: run {
                MovieDetailRedcomandBindingModel_()
                    .id(-currentPosition)
            }
        }else{
            return MovieDetailRedcomandBindingModel_()
                .id(-currentPosition)
        }
    }
}