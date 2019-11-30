package com.appetiser.apps.coding_challenge.domain.dagger.components

import android.content.Context
import com.appetiser.apps.coding_challenge.domain.dagger.modules.AppetiserModule
import com.appetiser.apps.coding_challenge.domain.dagger.modules.DatabaseModule
import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import com.appetiser.apps.coding_challenge.domain.network.AppetiserApi
import com.appetiser.apps.coding_challenge.viewmodel.data.db.AppDatabase
import com.bumptech.glide.RequestManager
import com.sunstar.ph.bestofcebu.domain.dagger.modules.ActivityModule
import com.sunstar.ph.bestofcebu.domain.dagger.modules.ContextModule
import com.sunstar.ph.bestofcebu.domain.dagger.modules.ImageModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable

/**
 *
 *
 */
@AppetiserScope
@Component(modules = [AppetiserModule::class, ActivityModule::class, ContextModule::class,  ImageModule::class, DatabaseModule::class])
interface AppetiserComponent {
    fun getGlide(): RequestManager
    fun getAppetiserApi(): AppetiserApi
    fun getSubscriptions(): CompositeDisposable
    fun getContext(): Context
    fun getDatabase(): AppDatabase

}