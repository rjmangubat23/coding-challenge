package com.appetiser.apps.coding_challenge.viewmodel.data

import com.appetiser.apps.coding_challenge.model.SearchResult

/**
 * Result Data data class to be used in conjuction with the ViewModel
 *
 * @property searchResult
 * @property message
 * @property error
 */
data class ResultData(val searchResult: SearchResult, val message: String, val error: Throwable? = null)