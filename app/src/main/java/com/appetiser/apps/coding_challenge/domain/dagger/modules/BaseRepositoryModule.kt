package com.appetiser.apps.coding_challenge.domain.dagger.modules

import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import com.sunstar.ph.bestofcebu.domain.dagger.scopes.BaseRepositoryScope
import dagger.Module
import dagger.Provides

/**
 * Dagger module which contains baseRepository
 *
 * @property baseRepository
 */
@Module
class BaseRepositoryModule(private val baseRepository: BaseRepositoryScope) {

    @Provides
    @AppetiserScope
    fun baseRepository(): BaseRepositoryScope {
        return baseRepository
    }

}