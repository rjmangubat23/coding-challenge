package com.appetiser.apps.coding_challenge.domain.dagger.modules

import com.appetiser.apps.coding_challenge.domain.dagger.scopes.AppetiserScope
import com.appetiser.apps.coding_challenge.view.base.BaseActivity
import dagger.Module
import dagger.Provides

/**
 *
 * Dagger module which contains BaseActivity
 *
 * @property baseActivity
 */
@Module
class BaseActivityModule(private val baseActivity: BaseActivity) {

    @Provides
    @AppetiserScope
    fun baseActivity(): BaseActivity {
        return baseActivity
    }

}