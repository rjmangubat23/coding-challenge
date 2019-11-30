package com.appetiser.apps.coding_challenge.model

/**
 * Result Data class
 *
 * @property artworkUrl100
 * @property longDescription
 * @property primaryGenreName
 * @property trackId
 * @property trackName
 * @property trackPrice
 * @property trackViewUrl
 */
data class Result(

    val artworkUrl100 : String,
    val longDescription : String,
    val primaryGenreName : String,
    val trackId : Int,
    val trackName : String,
    val trackPrice : Double,
    val trackViewUrl : String

)