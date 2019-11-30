package com.appetiser.apps.coding_challenge.view.base

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.appetiser.apps.coding_challenge.Appetiser
import com.appetiser.apps.coding_challenge.domain.dagger.components.BaseFragmentComponent
import com.appetiser.apps.coding_challenge.domain.dagger.components.DaggerBaseFragmentComponent
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * Class that contains the BaseFragment which is to be inherited to other activities.
 *
 */
abstract class BaseFragment : Fragment() {


    @Inject
    lateinit var glideManager: RequestManager

    @Inject
    lateinit var contextFromFragment: Context

    @Inject
    lateinit var subscriptions: CompositeDisposable

    /**
     * To be used to identify the fragment when it will be used inside a view
     */
    abstract fun getLabel(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { initializeDaggerComponent(it).injectFragment(this) }
    }

    /**
     * Initializes the dagger component which injects base activity of the glide(image), context,
     * subscriptions variables
     *
     * @param activity
     * @return
     */
    fun initializeDaggerComponent(activity: Activity): BaseFragmentComponent {
        return DaggerBaseFragmentComponent.builder()
            .appetiserComponent(Appetiser[activity].component())
            .build()
    }

    /**
     * Subscribes view to a viewmodel method to observe and update changes to the UI
     *
     * @param disposable
     * @return
     */
    fun subscribe(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }

    /**
     * Clears any existing subscriptions
     *
     */
    fun clearSubscriptions() {
        subscriptions.clear()
    }

    /**
     * Shows fragment wide snackbar
     *
     * @param message
     */
    fun showSnackBar(activity: FragmentActivity, message: String) {
        Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState()")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("onViewCreated()")

    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume()")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop()")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart()")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.i("onConfigurationChanged()")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Timber.i("onLowMemory()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("onDestroyView()")
        Timber.i("subscriptions cleared")
        clearSubscriptions()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy()")
    }


}
