package com.sunstar.ph.bestofcebu.domain.dagger.modules

import android.content.Context
import com.appetiser.apps.coding_challenge.domain.dagger.qualifiers.ApplicationContext
import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger module which contains Network Client initialization and methods
 *
 */
@Module(includes = [ContextModule::class])
@Singleton
class NetworkModule {

    @Provides
    @AppetiserScope
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.i(message) })
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @AppetiserScope
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1024 * 1024)) //10MB Cache
    }

    @Provides
    @AppetiserScope
    fun cacheFile(@ApplicationContext context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @AppetiserScope
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }

}
