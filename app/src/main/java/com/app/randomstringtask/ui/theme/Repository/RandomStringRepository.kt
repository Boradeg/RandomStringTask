package com.app.randomstringtask.ui.theme.Repository


import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.app.randomstringtask.ui.theme.Screen.RandomStringDisplay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject



object RandomStringProvider {
    private const val AUTHORITY = "com.iav.contestdataprovider"
    private const val PATH = "text"
    private val CONTENT_URI = Uri.parse("content://$AUTHORITY/$PATH")

    fun getRandomString(context: Context, maxLength: Int = 8): RandomStringDisplay? {
        return try {
            val bundle = Bundle().apply {
                putInt(ContentResolver.QUERY_ARG_LIMIT, maxLength)
            }

            val cursor = context.contentResolver.query(
                CONTENT_URI,
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
                    Log.d("TAG","$value")

                    return RandomStringDisplay(value, length, created)
                }
            }
            null
        } catch (e: Exception) {
            Log.e("RandomProvider", "Error: ${e.message}")
            null
        }
    }
}


