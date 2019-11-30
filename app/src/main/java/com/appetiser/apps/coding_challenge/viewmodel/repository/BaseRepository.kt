package com.appetiser.apps.coding_challenge.viewmodel.repository

import android.app.Activity
import com.appetiser.apps.coding_challenge.Appetiser
import com.appetiser.apps.coding_challenge.domain.dagger.components.DaggerBaseRepositoryComponent
import com.appetiser.apps.coding_challenge.domain.network.AppetiserApi
import com.appetiser.apps.coding_challenge.viewmodel.data.db.AppDatabase
import javax.inject.Inject

/**
 * Class that contains the BaseRepository which is to be inherited to other repositories.
 *
 * @constructor
 * TODO
 *
 * @param activity
 */
abstract class BaseRepository(activity: Activity) {

    @Inject
    lateinit var appetiserApi: AppetiserApi
    @Inject
    lateinit var appDatabase: AppDatabase

    init {
        /**
         *
         * Initializes the dagger component which injects base activity of the appetiserApi and
         * AppDatabase
         */
        DaggerBaseRepositoryComponent.builder()
            .appetiserComponent(Appetiser[activity].component())
            .build().injectRepository(this)
    }



}