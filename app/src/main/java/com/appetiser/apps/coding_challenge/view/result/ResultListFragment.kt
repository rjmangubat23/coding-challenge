package com.appetiser.apps.coding_challenge.view.result

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.appetiser.apps.coding_challenge.R
import com.appetiser.apps.coding_challenge.domain.utilities.Constants
import com.appetiser.apps.coding_challenge.model.Result
import com.appetiser.apps.coding_challenge.view.base.BaseFragment
import com.appetiser.apps.coding_challenge.viewmodel.ResultViewModel
import com.appetiser.apps.coding_challenge.viewmodel.repository.ResultRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_result_list.*
import timber.log.Timber

/**
 * Contains Fragment that displays the list of results from the SearchResult response
 *
 */
class ResultListFragment : BaseFragment(),  ResultListAdapter.ItemClickListener  {

    private val label = "ResultListFragment"

    private lateinit var resultRepository : ResultRepository
    private lateinit var resultViewModel: ResultViewModel
    private var lastClickTime: Long = 0

    private lateinit var resultListAdapter: ResultListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        resultRepository = ResultRepository(activity!!)
        resultViewModel = ResultViewModel(resultRepository)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_result_list, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        //Subcribe to the resultViewModel to get the search results from Repository
        subscribe(resultViewModel.getSearchResults()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Timber.d("Received resultViewModel $it.")

                //Populates result list to recyclerview
                resultListAdapter = ResultListAdapter(activity!!, it.searchResult, glideManager , this)
                val linearLayoutManager = LinearLayoutManager(activity!!)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerViewResults.layoutManager = linearLayoutManager
                recyclerViewResults.adapter = resultListAdapter
                resultListAdapter.notifyDataSetChanged()

                hideLoading()
            }, { it ->
                Timber.w(it)
                it.printStackTrace()
            })
        )
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerViewResults.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        recyclerViewResults.visibility = View.VISIBLE
    }

    /**
     * Responsible for each item click in the result list
     *
     * @param result individual result coming from the result list
     */
    override fun itemClick(result: Result) {
        // Preventing multiple clicks, using threshold of 250 ms
        if (SystemClock.elapsedRealtime() - lastClickTime < 250) {
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        val intent = Intent(activity, ResultInfoActivity::class.java)
        intent.putExtra(Constants.Track.ARTWORK, result.artworkUrl100)
        intent.putExtra(Constants.Track.NAME, result.trackName)
        intent.putExtra(Constants.Track.GENRE, result.primaryGenreName)
        intent.putExtra(Constants.Track.PRICE, result.trackPrice)
        intent.putExtra(Constants.Track.URL, result.trackViewUrl)
        intent.putExtra(Constants.Track.DESCRIPTION, result.longDescription)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity!!.startActivity(intent)
    }

    /**
     * Labels the fragment to be used in a view
     *
     * @return label string
     */
    override fun getLabel(): String {
        return label
    }

    /**
     * newInstance constructor for creating fragment with arguments
     *
     */
    companion object {
        fun newInstance(): ResultListFragment {
            return ResultListFragment()
        }
    }
}