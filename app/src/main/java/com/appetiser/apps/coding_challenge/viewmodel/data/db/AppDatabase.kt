package com.appetiser.apps.coding_challenge.viewmodel.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appetiser.apps.coding_challenge.model.SearchResult

/**
 * Contains app database class to be used
 *
 */
@Database(entities = [SearchResult::class], version = 1, exportSchema = false)
@TypeConverters(ResultConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchResultDao(): ResultDao
}