package com.app.moviedb.features.detail.controller

import com.airbnb.epoxy.EpoxyModel
import com.app.moviedb.MovieDetailVideoBindingModel_
import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieDetailCategory
import com.app.moviedb.core.model.MovieModel
import com.app.moviedb.core.model.getPosterUrl
import com.app.moviedb.features.home.homeinterface.MovieClickListener

class MovieDetailVideoListController(
    movieCategory: MovieDetailCategory,
    clickListener: MovieClickListener? = null
): MovieDetailCarouselController(movieCategory, clickListener) {
    override fun buildItemModel(currentPosition: Int, item: BaseModel?): EpoxyModel<*> {
        if(item is MovieModel){
            return item.run {
                MovieDetailVideoBindingModel_()
                    .id("${movieCategory}${this.id}")
                    .movieId(this.id)
                    .posterImage(this.getPosterUrl())
                    .clickListener(clickListener)
            } ?: run {
                MovieDetailVideoBindingModel_()
                    .id(-currentPosition)
            }
        }else{
            return MovieDetailVideoBindingModel_()
                .id(-currentPosition)
        }
    }
}