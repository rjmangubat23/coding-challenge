package com.appetiser.apps.coding_challenge.viewmodel.data.db

import androidx.room.TypeConverter
import com.appetiser.apps.coding_challenge.model.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Converts Result List to Json/Gson and vice versa
 *
 */
public class ResultConverters {

    /**
     * Convert Results to a Json
     */
    @TypeConverter
    fun fromResultToJson(results: List<Result>): String {
        return Gson().toJson(results)
    }

    /**
     * Convert a json to Results
     */

    @TypeConverter
    fun toResult(resultString: String): List<Result> {
        val resultsType = object : TypeToken<List<Result>>() {}.type
        return Gson().fromJson<List<Result>>(resultString, resultsType)
    }


}