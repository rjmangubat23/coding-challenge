package com.appetiser.apps.coding_challenge.domain.dagger.components

import com.appetiser.apps.coding_challenge.domain.dagger.modules.BaseFragmentModule
import com.appetiser.apps.coding_challenge.domain.dagger.scopes.BaseFragmentScope
import com.appetiser.apps.coding_challenge.view.base.BaseFragment
import dagger.Component

@BaseFragmentScope
@Component(dependencies = [AppetiserComponent::class], modules = [BaseFragmentModule::class])
interface BaseFragmentComponent {
    fun injectFragment(baseFragment: BaseFragment)
}