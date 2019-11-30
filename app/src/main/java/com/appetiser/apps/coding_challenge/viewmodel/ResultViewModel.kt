package com.appetiser.apps.coding_challenge.viewmodel

import androidx.lifecycle.ViewModel
import com.appetiser.apps.coding_challenge.viewmodel.data.ResultData
import com.appetiser.apps.coding_challenge.viewmodel.repository.ResultRepository
import io.reactivex.Flowable

/**
 * View model of the result data response
 *
 * @property resultRepository
 */
class ResultViewModel(private val resultRepository: ResultRepository) : ViewModel() {
    fun getSearchResults(): Flowable<ResultData>{
        return resultRepository.getSearchResults().map {
            ResultData(it, "Result Data")
        }

    }
}