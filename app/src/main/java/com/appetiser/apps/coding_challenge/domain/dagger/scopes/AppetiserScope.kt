package com.appetiser.apps.coding_challenge.domain.dagger.scopes

import javax.inject.Scope

/**
 * Identifies Scope of Appetiser where
 * containing an injectable constructor and governs how the injector reuses
 * instances of the Appetiser Scope type.
 *
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppetiserScope
