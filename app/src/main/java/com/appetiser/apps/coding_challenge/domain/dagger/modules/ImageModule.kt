package com.sunstar.ph.bestofcebu.domain.dagger.modules

import android.content.Context
import com.appetiser.apps.coding_challenge.domain.dagger.qualifiers.ApplicationContext
import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Dagger module which contains Image methods via Glide
 *
 */
@Module(includes = [ContextModule::class])
@Singleton
class ImageModule {

    @Provides
    @AppetiserScope
    fun glide(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }


}
