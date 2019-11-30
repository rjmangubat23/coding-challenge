package com.appetiser.apps.coding_challenge.viewmodel.repository

import android.app.Activity
import com.appetiser.apps.coding_challenge.domain.utilities.Constants.ALL
import com.appetiser.apps.coding_challenge.domain.utilities.Constants.AU
import com.appetiser.apps.coding_challenge.domain.utilities.Constants.MOVIE
import com.appetiser.apps.coding_challenge.domain.utilities.Constants.STAR
import com.appetiser.apps.coding_challenge.model.SearchResult
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ResultRepository(activity: Activity) : BaseRepository(activity){

    /**
     * Concats the Search Result API Response and data response in the App Database
     *
     * @return Concatted Flowable Search Result
     */
    fun getSearchResults(): Flowable<SearchResult> {
        return Flowable.concat(
            getSearchResultsFromApi(),
            getSearchResultsFromDb()
        )
    }

    /**
     * Gets the searchResult from the Itunes Search API
     *
     * @return Flowable Search Result
     */
    fun getSearchResultsFromApi(): Flowable<SearchResult> {
        return appetiserApi.getSearchResults(STAR, AU, MOVIE, ALL)
            .doOnNext {
                Timber.d("Dispatching $it info from API...")
                storeSearchResultInDB(it)
            }
    }

    /**
     * Gets the searchResult from the App Database
     *
     * @return Flowable Search Result
     */
    fun getSearchResultsFromDb(): Flowable<SearchResult> {
        return appDatabase.searchResultDao().getSearchResult()
            .doOnNext {
                Timber.d("Dispatching ${it.resultCount} results from DB...")
            }
    }

    /**
     * Stores searchResult response to the App Database
     *
     * @param searchResult
     */
    private fun storeSearchResultInDB(searchResult: SearchResult) {
        Observable.fromCallable { appDatabase.searchResultDao().insert(searchResult) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Timber.d("Inserted ${searchResult.resultCount} results from API in DB...")
            }
    }


}