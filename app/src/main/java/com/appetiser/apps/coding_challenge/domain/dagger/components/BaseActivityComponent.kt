package com.appetiser.apps.coding_challenge.domain.dagger.components

import com.appetiser.apps.coding_challenge.domain.dagger.modules.BaseActivityModule
import com.appetiser.apps.coding_challenge.view.base.BaseActivity
import com.sunstar.ph.bestofcebu.domain.dagger.scopes.BaseActivityScope
import dagger.Component

@BaseActivityScope
@Component(dependencies = [AppetiserComponent::class], modules = [BaseActivityModule::class])
interface BaseActivityComponent {
    fun injectActivity(baseActivity : BaseActivity)
}