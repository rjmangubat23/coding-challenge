package com.appetiser.apps.coding_challenge.viewmodel.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appetiser.apps.coding_challenge.model.SearchResult
import io.reactivex.Flowable

/**
 * Contains ResultDao, which is communicates with the app database
 *
 */
@Dao
interface ResultDao {
    /*
    *
    *  Featured dao methods
    */

    @Query("SELECT * FROM searchResult")
    fun getSearchResult(): Flowable<SearchResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchResult: SearchResult)

    @Query("DELETE FROM searchResult")
    fun delete()

}
