package com.app.moviedb.features.detail.controller

import android.util.Log
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.app.moviedb.MovieDetailCommentBindingModel_
import com.app.moviedb.core.model.*
import com.app.moviedb.features.home.homeinterface.MovieClickListener

class MovieDetailCommentController(
    protected val movieCategory: MovieDetailCategory,
    protected val clickListener: MovieClickListener? = null
) : PagedListEpoxyController<BaseModel>()  {
    override fun buildItemModel(currentPosition: Int, item: BaseModel?): EpoxyModel<*> {
        Log.e("TEST_DATA","item= $item")
        if(item is AuthorModel){
            return item.run {
                MovieDetailCommentBindingModel_()
                    .id("${movieCategory}${this.id}")
                    .movieId(this.id)
                    .name(this.displayName())
                    .content(this.content)
                    .createdAt(this.createdAt)
                    .avatarPath(this.getAvatarUrl())
                    .rating(this.display5StarsRating())
                    .clickListener(clickListener)
            } ?: run {
                MovieDetailCommentBindingModel_()
                    .id(-currentPosition)
            }
        }else{
            return MovieDetailCommentBindingModel_()
                .id(-currentPosition)
        }
    }
}