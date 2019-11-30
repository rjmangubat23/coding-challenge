package com.appetiser.apps.coding_challenge.view.base

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.appetiser.apps.coding_challenge.Appetiser
import com.appetiser.apps.coding_challenge.domain.dagger.components.BaseActivityComponent
import com.appetiser.apps.coding_challenge.domain.dagger.components.DaggerBaseActivityComponent
import com.appetiser.apps.coding_challenge.domain.utilities.Constants
import com.appetiser.apps.coding_challenge.domain.utilities.PreferenceHelper
import com.appetiser.apps.coding_challenge.domain.utilities.PreferenceHelper.get
import com.appetiser.apps.coding_challenge.domain.utilities.PreferenceHelper.set
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber.i
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 *
 * Class that contains the BaseActivity which is to be inherited to other activities.
 *
 */
abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var glideManager: RequestManager

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var subscriptions : CompositeDisposable

    private val simpleDateFormat = SimpleDateFormat("MMM dd, YYYY", Locale.US)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDaggerComponent(this).injectActivity(this)
    }

    /**
     * Initializes the dagger component which injects base activity of the glide(image), context,
     * subscriptions variables
     *
     * @param activity
     * @return
     */
    private fun initializeDaggerComponent(activity: Activity): BaseActivityComponent {
        return DaggerBaseActivityComponent.builder()
            .appetiserComponent(Appetiser[activity].component())
            .build()
    }

    override fun onStart() {
        super.onStart()
        i("onStart()")

    }

    override fun onResume() {
        super.onResume()
        i("onResume()")

    }
    override fun onPause() {
        super.onPause()
        i("onPause()")

    }

    override fun onStop() {
        super.onStop()
        i("onStop()")

    }

    override fun onRestart() {
        super.onRestart()
        i("onRestart()")

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        i("onAttachedToWindow()")
    }

    override fun onPostResume() {
        super.onPostResume()
        i("onPostResume()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        i("onSaveInstanceState()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        i("onConfigurationChanged()")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        i("onLowMemory()")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        i("onTrimMemory()")
    }

    override fun onDestroy() {
        saveLastDateVisited()
        super.onDestroy()
        i("onDestroy()")

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * Initializes custom toolbar
     *
     * @param toolbar toolbar to be initialized
     * @param isBackEnabled flag that shows/hides back button
     */
    fun initializeToolbar(toolbar: Toolbar, isBackEnabled : Boolean) {
        hideToolbar()
        setSupportActionBar(toolbar)
        displayToolbarBackButton(isBackEnabled)
        hideSupportActionBarTitle()

    }

    /**
     * Hides support action toolbar
     *
     */
    private fun hideToolbar() {
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

    }
    /**
     * Hides support action toolbar title
     *
     */
    private fun hideSupportActionBarTitle() {
        supportActionBar!!.setDisplayShowTitleEnabled(false)

    }

    /**
     * Displays back button in toolbar if enabled
     *
     * @param isBackEnabled flag that shows/hides back button
     */
    private fun displayToolbarBackButton(isBackEnabled: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(isBackEnabled)
        supportActionBar!!.setDisplayShowHomeEnabled(isBackEnabled)

    }

    /**
     * Shows activity wide snackbar
     *
     * @param message
     */
    fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
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
    fun clearSubscriptions(){
        subscriptions.clear()
        i("subscriptions cleared")
    }

    /**
     * Saves the last Date visited of the app
     *
     */
    fun saveLastDateVisited() {
        val pref =  PreferenceHelper.instance(context)
        val dateLastVisited: String? = pref[Constants.Shared.DATE_LAST_VISITED]

        if(dateLastVisited != null){
            val dateVisited: Date = simpleDateFormat.parse(dateLastVisited)
            if(System.currentTimeMillis() > dateVisited.time){
                pref[Constants.Shared.DATE_LAST_VISITED] = dateNow()
            }
        }else{
            pref[Constants.Shared.DATE_LAST_VISITED] = dateNow()

        }
    }

    /**
     * Method containing Date now string
     *
     * @return dateNow String
     */
    fun dateNow() : String {
        val date = Date(System.currentTimeMillis())
        return simpleDateFormat.format(date)
    }

}
