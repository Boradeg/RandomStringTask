package com.app.randomstringtask.Data.provider


import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.app.randomstringtask.Data.mapper.RandomStringForData
import com.app.randomstringtask.utils.Constants
import org.json.JSONObject

object RandomStringProvider {

    fun getRandomString(context: Context, maxLength: Int = 8): RandomStringForData? {
        return try {
            val bundle = Bundle().apply {
                putInt(ContentResolver.QUERY_ARG_LIMIT, maxLength)
            }
            val cursor = context.contentResolver.query(
               Constants.CONTENT_URI,
                null,
                bundle,
                null
            )

            cursor?.use {
                if (it.moveToFirst()) {
                    val jsonData = it.getString(it.getColumnIndexOrThrow("data"))
                    val json = JSONObject(jsonData).getJSONObject("randomText")

                    val value = json.getString("value")
                    val length = json.getInt("length")
                    val created = json.getString("created")
                    return RandomStringForData(value, length, created)
                }
            }
            null
        } catch (e: Exception) {
            Log.e("RandomProvider", "Error: ${e.message}")
            null
        }
    }
}


