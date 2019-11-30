package com.appetiser.apps.coding_challenge.view.result

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appetiser.apps.coding_challenge.R
import com.appetiser.apps.coding_challenge.model.Result
import com.appetiser.apps.coding_challenge.model.SearchResult
import com.bumptech.glide.RequestManager

/**
 * Contains Adapter class for the result list populated from the SearchResult
 *
 * @property context
 * @property searchResult
 * @property glide
 * @property itemClickListener
 */
class ResultListAdapter(val context: Context, val searchResult : SearchResult, val glide: RequestManager,
                        val itemClickListener: ItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultListViewHolder {
        val view = inflater.inflate(R.layout.item_result_list, parent, false)
        return ResultListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val resultViewHolder = holder as ResultListViewHolder
        val result = searchResult.results[position]
        resultViewHolder.bindView(result, glide, itemClickListener)
    }

    override fun getItemCount(): Int {
        return searchResult.resultCount
    }

    /**
     * To be used to simulate item clicks of an individual result
     *
     */
    interface ItemClickListener {
        fun itemClick(result : Result)
    }
}
