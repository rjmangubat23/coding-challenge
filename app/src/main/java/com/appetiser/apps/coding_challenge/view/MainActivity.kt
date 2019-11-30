package com.appetiser.apps.coding_challenge.view

import android.os.Bundle
import androidx.annotation.IdRes
import com.appetiser.apps.coding_challenge.R
import com.appetiser.apps.coding_challenge.domain.utilities.Constants
import com.appetiser.apps.coding_challenge.domain.utilities.PreferenceHelper
import com.appetiser.apps.coding_challenge.domain.utilities.PreferenceHelper.get
import com.appetiser.apps.coding_challenge.view.base.BaseActivity
import com.appetiser.apps.coding_challenge.view.base.BaseFragment
import com.appetiser.apps.coding_challenge.view.result.ResultListFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.ConcurrentHashMap

class MainActivity : BaseActivity() {

    private lateinit var resultListFragment: ResultListFragment

    private val mapOfAddedFragments = ConcurrentHashMap<String, BaseFragment>()
    private var backPressedDuration: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializes toolbar and display last visit in toolbar text
        initializeToolbar(toolbarResults, false)
        displayLastVisit()

        //creates new instance of resultListFragment and displays it to activity
        resultListFragment = ResultListFragment.newInstance()
        displayFragment(resultListFragment)
    }

    /**
     * Displays last visit date into the toolbar title text
     *
     */
    private fun displayLastVisit() {
        val dateLastVisited: String? =
            PreferenceHelper.instance(context)[Constants.Shared.DATE_LAST_VISITED]
        var dateString = ""

        if (dateLastVisited != null) {
            dateString = "Your last visit was on: $dateLastVisited"
        } else {
            dateString = "Your last visit was on: ${dateNow()}"
        }
        toolbarTitle.text = dateString
    }

    /**
     * Loads a fragment using show a fragment
     * @param fragment
     */
    private fun displayFragment(fragment: BaseFragment) {
        if (!mapOfAddedFragments.containsKey(fragment.getLabel()))
            mapOfAddedFragments.put(fragment.getLabel(), fragment)

        showFragment(fragment.getLabel(), R.id.containerMain)
    }

    /**
     * Displays a fragment and hides all the other ones
     * @param fragmentTag is the tag of the fragment we want to display
     */
    private fun showFragment(fragmentTag: String, @IdRes containerViewId: Int) {
        val ft = this.supportFragmentManager.beginTransaction()
        val fragment: BaseFragment? = mapOfAddedFragments[fragmentTag]

        if (fragment != null) {
            if (fragment.isAdded)
                ft.show(fragment)
            else { //fragment needs to be added to the frame container
                ft.add(containerViewId, fragment, fragment.getLabel())
            }
        } else {
            //the chosen fragment doesn't exist
            return
        }

        //we hide the other fragments
        mapOfAddedFragments.forEach { (key, value) ->
            if (!key.equals(fragmentTag)) {
                val fragmentTemp = value
                // Hide the other fragments
                if (fragmentTemp.isAdded)
                    ft.hide(fragmentTemp)
            }

        }

        //commit changes
        ft.commit()
    }

    /**
     * Simulates a delay back press to confirm exit, this is to simulate a saving of last date
     * visited if user opts out to exit app
     *
     */
    override fun onBackPressed() {
        val currentMillis = System.currentTimeMillis()
        if (backPressedDuration + 3000 > currentMillis) {
            saveLastDateVisited()
            super.onBackPressed()
        } else {
            try {
                var displayPressAgain = getString(R.string.backspace_pressagain)
                if (displayPressAgain.length > 0) {
                    displayPressAgain = displayPressAgain.trim { it <= ' ' }
                }
                showSnackBar(displayPressAgain)
                backPressedDuration = currentMillis
            } catch (err: Exception) {
                err.printStackTrace()
            }

        }

    }
}
