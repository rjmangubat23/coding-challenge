package com.sunstar.ph.bestofcebu.domain.dagger.modules

import android.app.Activity
import android.content.Context
import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Dagger module which contains the activity context to be injected
 *
 * @property context
 */
@Module
class ActivityModule(private val context: Activity) {

    @Provides
    @AppetiserScope
    @Named("activity_context")
    fun context(): Context {
        return context
    }
}