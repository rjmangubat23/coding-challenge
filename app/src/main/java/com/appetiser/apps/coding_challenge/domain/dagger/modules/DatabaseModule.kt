package com.appetiser.apps.coding_challenge.domain.dagger.modules

import android.content.Context
import androidx.room.Room
import com.appetiser.apps.coding_challenge.domain.dagger.qualifiers.ApplicationContext
import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import com.appetiser.apps.coding_challenge.domain.utilities.Constants
import com.appetiser.apps.coding_challenge.viewmodel.data.db.AppDatabase
import com.sunstar.ph.bestofcebu.domain.dagger.modules.ContextModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Dagger module which contains the Database
 *
 */
@Module(includes = [ContextModule::class])
@Singleton
class DatabaseModule {

    @Provides
    @AppetiserScope
    fun appDatabase(@ApplicationContext context: Context): AppDatabase {

        return Room.databaseBuilder(context,
                AppDatabase::class.java, Constants.DB)
                .fallbackToDestructiveMigration()
                .build()
    }



}
