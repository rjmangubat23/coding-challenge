package com.appetiser.apps.coding_challenge.domain.utilities

/**
 * Contains constants that are used within the Appetiser Application
 */
object Constants {

    //Api related
    const val BASE_URL = "https://itunes.apple.com/"
    const val STAR = "star"
    const val AU = "au"
    const val MOVIE = "movie"
    const val ALL = "all"
    const val DB: String = "appetiser-db"
    const val PREF: String = "appetiser-pref"

    object Track{
        const val ARTWORK = "artwork"
        const val NAME = "name"
        const val GENRE = "genre"
        const val PRICE = "price"
        const val URL = "url"
        const val DESCRIPTION = "description"

    }
    object Shared{
        const val DATE_LAST_VISITED = "date-last-visited"
    }

}