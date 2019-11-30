package com.appetiser.apps.coding_challenge.domain.dagger.modules

import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import com.appetiser.apps.coding_challenge.view.base.BaseFragment
import dagger.Module
import dagger.Provides

/**
 * Dagger module which contains BaseFragment
 *
 * @property baseFragment
 */
@Module
class BaseFragmentModule(private val baseFragment: BaseFragment) {

    @Provides
    @AppetiserScope
    fun baseFragment(): BaseFragment {
        return baseFragment
    }

}