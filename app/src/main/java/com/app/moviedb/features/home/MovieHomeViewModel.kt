package com.app.moviedb.features.home

import com.app.moviedb.core.model.BaseModel
import com.app.moviedb.core.model.MovieCategory
import com.app.moviedb.core.network.utils.Listing
import com.app.moviedb.core.network.utils.NetworkState
import com.app.moviedb.core.repository.AppRepository
import com.app.moviedb.core.viewmodel.BaseViewModel
import io.reactivex.Observable
import org.koin.core.inject

class MovieHomeViewModel : BaseViewModel(){

    private val appRepo: AppRepository by inject()
    private val listingMap = mutableMapOf<MovieCategory, Listing<BaseModel>>()

    fun fetchList(category: MovieCategory): Listing<BaseModel> {
//        val listing = appRepository.apiRepository.fetchMovieList(category)
        val listing = appRepo.apiRepository.fetchMovieList(category)
        listingMap[category] = listing
        return listing
    }

    fun refresh() {
        listingMap.values.forEach {
            it.refresh.invoke()
        }
    }


    /**
     * Merge all category refresh state into one state
     */
    fun refreshState(): Observable<NetworkState> {
        val stateList = mutableListOf<Observable<NetworkState>>()
        listingMap.values.forEach {
            it.refreshState?.apply {
                stateList.add(this)
            }
        }
        return Observable.combineLatest(stateList) { states ->
            var mergedState = NetworkState.IDLE
            states.forEach {
                val s = it as NetworkState
                if (s == NetworkState.LOADING) {
                    mergedState = NetworkState.ERROR
                }
            }
            mergedState

        }
    }
}