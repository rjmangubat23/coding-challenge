package com.appetiser.apps.coding_challenge.domain.dagger.scopes

import javax.inject.Scope

/**
 * Identifies Scope of BaseFragment where
 * containing an injectable constructor and governs how the injector reuses
 * instances of the BaseFragment Scope type.
 *
 */
@Scope
annotation class BaseFragmentScope
