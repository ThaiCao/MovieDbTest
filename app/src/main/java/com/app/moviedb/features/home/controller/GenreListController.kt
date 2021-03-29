package com.app.moviedb.features.home.controller

import android.util.Log
import com.airbnb.epoxy.EpoxyModel
import com.app.moviedb.MovieHomeGenreBindingModel_
import com.app.moviedb.R
import com.app.moviedb.core.model.*
import com.app.moviedb.features.home.homeinterface.GenreClickListener

class GenreListController (
    movieCategory: MovieCategory,
    clickListener: GenreClickListener? = null
)
    : GenreCarouselController(movieCategory, clickListener) {
    override fun buildItemModel(currentPosition: Int, item: BaseModel?): EpoxyModel<*> {
        val backgroundItem : Int

        when(currentPosition % 5){
            0 ->{
                backgroundItem = R.drawable.background_genre_item_1
            }
            1 ->{
                backgroundItem = R.drawable.background_genre_item_2
            }
            2 ->{
                backgroundItem = R.drawable.background_genre_item_3
            }
            3 ->{
                backgroundItem = R.drawable.background_genre_item_4
            }
            else ->{
                backgroundItem = R.drawable.background_genre_item_5
            }
        }
        Log.e("TEST_DATA","currentPosition= $currentPosition --- currentPosition % 5 =${currentPosition % 5} - backgroundItem= $backgroundItem")
        if(item is GenreModel){
            return item.run {
                MovieHomeGenreBindingModel_()
                    .id("${movieCategory}${this.id}")
                    .genreId(this.id)
                    .title(this.name)
                    .background(backgroundItem)
                    .clickListener(clickListener)
            } ?: run {
                MovieHomeGenreBindingModel_()
                    .id(-currentPosition)
            }
        }else{
            return item.run {
                MovieHomeGenreBindingModel_()
                    .id(-currentPosition)
            }
        }

    }
}