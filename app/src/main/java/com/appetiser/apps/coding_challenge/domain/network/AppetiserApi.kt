package com.appetiser.apps.coding_challenge.domain.network

import com.appetiser.apps.coding_challenge.model.SearchResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Contains the Appetiser Network that calls iTunes Search API
 *
 */
interface AppetiserApi{

    @GET("search")
    fun getSearchResults(@Query("term") term : String, @Query("country")country: String,@Query("media")media: String,
               @Query("") entity: String): Flowable<SearchResult>

}