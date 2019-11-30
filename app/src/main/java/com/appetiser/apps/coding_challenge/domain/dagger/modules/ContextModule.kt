package com.sunstar.ph.bestofcebu.domain.dagger.modules

import android.content.Context
import com.appetiser.apps.coding_challenge.domain.dagger.qualifiers.ApplicationContext
import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import dagger.Module
import dagger.Provides

/**
 * Dagger module which contains Application Context to be injected
 *
 * @constructor
 * initializes the application context
 *
 * @param context
 */
@Module
class ContextModule(context: Context) {

    @get:Provides
    @get:AppetiserScope
    val context: Context

    init {
        this.context = context.applicationContext
    }

    @Provides
    @AppetiserScope
    @ApplicationContext
    fun context(): Context {
        return context
    }
}
