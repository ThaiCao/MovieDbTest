package com.app.moviedb.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.app.moviedb.core.model.*
import com.app.moviedb.core.network.utils.Listing
import com.app.moviedb.core.repository.AppRepository
import com.app.moviedb.core.viewmodel.BaseViewModel
import io.reactivex.schedulers.Schedulers
import org.koin.core.inject

class MovieDetailViewModel : BaseViewModel(){
    private val appRepo: AppRepository by inject()
    private val _movieDetail = MutableLiveData<MovieModel>()
    private val listingMap = mutableMapOf<MovieDetailCategory, Listing<BaseModel>>()

    val posterUrl: LiveData<String> = Transformations.map(_movieDetail) { it.getPosterUrl() }
    val backdropUrl: LiveData<String> = Transformations.map(_movieDetail) { it.getBackdropUrl() }
    val title: LiveData<String> = Transformations.map(_movieDetail) { it.displayTitle() }
    val rating: LiveData<Float> = Transformations.map(_movieDetail) { it.display5StarsRating() }
    val voteCount: LiveData<String> = Transformations.map(_movieDetail) { it.displayVoteCount() }
    val overview: LiveData<String> = Transformations.map(_movieDetail) { it.displayOverview() }
    val releaseDate: LiveData<String> =
        Transformations.map(_movieDetail) { it.displayReleaseDate() }

    fun fetchMovieDetail(id: String) {
        appRepo.apiRepository.fetchMovieDetail(id)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { _movieDetail.postValue(it) }
            .subscribe()
            .disposeOnCleared()
    }

    fun fetchList(movieId: String, category: MovieDetailCategory): Listing<BaseModel> {
        val listing = appRepo.apiRepository.fetchMovieDetailList(movieId, category)
        listingMap[category] = listing
        return listing
    }

    fun refresh() {
        listingMap.values.forEach {
            it.refresh.invoke()
        }
    }

}