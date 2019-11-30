package com.appetiser.apps.coding_challenge.view.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.appetiser.apps.coding_challenge.R
import com.appetiser.apps.coding_challenge.domain.utilities.Constants
import com.appetiser.apps.coding_challenge.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_result_info.*

/**
 * Contains Activity class which shows the individual Result info
 *
 */
class ResultInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_info)

        initializeToolbar(toolbarResultInfo, true)

        textViewTrackName.text =  intent.getStringExtra(Constants.Track.NAME)
        textViewGenre.text = intent.getStringExtra(Constants.Track.GENRE)
        textViewDescription.text = intent.getStringExtra(Constants.Track.DESCRIPTION)

        glideManager.load(intent.getStringExtra(Constants.Track.ARTWORK)).into(imageViewArtwork)

        buttonPrice.apply {
            val price = intent.getDoubleExtra(Constants.Track.PRICE, 0.0)
            text = "Buy from $${price}"
            setOnClickListener {view ->
                launchUrl(intent.getStringExtra(Constants.Track.URL))
            }
        }

    }


    /**
     * Launches URL to web application
     *
     * @param url
     */
    private fun launchUrl(url: String) {
        val uriUrl = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }
}
