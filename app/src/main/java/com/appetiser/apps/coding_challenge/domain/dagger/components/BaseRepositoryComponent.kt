package com.appetiser.apps.coding_challenge.domain.dagger.components

import com.appetiser.apps.coding_challenge.domain.dagger.modules.BaseRepositoryModule
import com.appetiser.apps.coding_challenge.viewmodel.repository.BaseRepository
import com.sunstar.ph.bestofcebu.domain.dagger.scopes.BaseRepositoryScope
import dagger.Component

@BaseRepositoryScope
@Component(dependencies = [AppetiserComponent::class], modules = [BaseRepositoryModule::class])
interface BaseRepositoryComponent {
    fun injectRepository(baseRepository: BaseRepository)
}