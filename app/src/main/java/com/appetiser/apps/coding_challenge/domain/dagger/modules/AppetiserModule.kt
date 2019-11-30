package com.appetiser.apps.coding_challenge.domain.dagger.modules

import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import com.appetiser.apps.coding_challenge.domain.network.AppetiserApi
import com.appetiser.apps.coding_challenge.domain.utilities.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sunstar.ph.bestofcebu.domain.dagger.modules.NetworkModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger module which includes the network apis, Rx Subscription are found
 *
 *
 */
@Module(includes = [NetworkModule::class])
@Singleton
class AppetiserModule {

    @Provides
    @AppetiserScope
    fun appetiserApi(bestOfCebuRetrofit: Retrofit): AppetiserApi {
        return bestOfCebuRetrofit.create<AppetiserApi>(AppetiserApi::class.java)
    }

    @Provides
    @AppetiserScope
    fun subscriptions(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @AppetiserScope
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @AppetiserScope
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()
    }
}
