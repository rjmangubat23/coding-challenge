package com.appetiser.apps.coding_challenge

import android.app.Activity
import android.app.Application
import com.appetiser.apps.coding_challenge.domain.dagger.components.AppetiserComponent
import com.appetiser.apps.coding_challenge.domain.dagger.components.DaggerAppetiserComponent
import com.appetiser.apps.coding_challenge.domain.network.AppetiserApi
import com.bumptech.glide.RequestManager
import com.sunstar.ph.bestofcebu.domain.dagger.modules.ContextModule
import io.reactivex.disposables.CompositeDisposable


/**
 * Contains Appetiser Application class
 *
 */
class Appetiser : Application() {

    private lateinit var instance: Appetiser
    private lateinit var component: AppetiserComponent

    private lateinit var appetiserApi: AppetiserApi
    private lateinit var subscriptions: CompositeDisposable
    private lateinit var glide: RequestManager

    override fun onCreate() {
        super.onCreate()

        /*
        //Uncomment to use the Timber log
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())*/

        component = DaggerAppetiserComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()

        appetiserApi = component.getAppetiserApi()
        glide = component.getGlide()
        subscriptions = component.getSubscriptions()

    }

    /**
     * Gets instance of Appetiser Application
     *
     */
    companion object Instance {
        operator fun get(activity: Activity): Appetiser {
            return activity.application as Appetiser
        }

    }

    /**
     * Returns Appetiser Component
     *
     * @return
     */
    fun component(): AppetiserComponent {
        return component
    }

}



