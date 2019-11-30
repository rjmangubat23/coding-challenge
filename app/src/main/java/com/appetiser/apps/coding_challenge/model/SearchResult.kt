package com.appetiser.apps.coding_challenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.appetiser.apps.coding_challenge.viewmodel.data.db.ResultConverters

/**
 * Search Result Data Class
 *
 * @property resultCount
 * @property results
 */
@Entity(tableName = "searchResult")
data class SearchResult(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "resultCount")
    val resultCount : Int,
    @ColumnInfo(name = "results")
    @TypeConverters(ResultConverters::class)
    val results : List<Result>
)