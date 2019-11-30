package com.appetiser.apps.coding_challenge.view.result

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appetiser.apps.coding_challenge.model.Result
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.item_result_list.view.*

/**
 * Contains view holder of each individual result about to be populated to the result list
 *
 * @constructor initializes the view to be updated
 *
 * @param itemView of the viewholder
 */
class ResultListViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    fun bindView(result : Result, glide: RequestManager, itemClickListener: ResultListAdapter.ItemClickListener){
        itemView.trackName.text = result.trackName
        itemView.genre.text = result.primaryGenreName
        itemView.price.text = "$${result.trackPrice}"

        glide.load(result.artworkUrl100).into(itemView.artwork)

        itemView.setOnClickListener {
            itemClickListener.itemClick(result)
        }
    }
}

