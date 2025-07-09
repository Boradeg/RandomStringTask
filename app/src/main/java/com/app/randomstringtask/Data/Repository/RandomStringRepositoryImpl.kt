package com.app.randomstringtask.Data.Repository

import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.app.randomstringtask.Data.local.RandomStringDao
import com.app.randomstringtask.Data.local.RandomStringEntity
import com.app.randomstringtask.Data.mapper.toEntity
import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import com.app.randomstringtask.Data.mapper.RandomStringForData
import com.app.randomstringtask.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONObject
import javax.inject.Inject

class RandomStringRepositoryImpl @Inject constructor(
    private val dao: RandomStringDao
) : RandomStringRepository {

    override fun observeAllRandomStrings(): Flow<List<RandomStringDisplay>> {
        return dao.observeAllRandomStrings().map { list ->
            list.map {
                RandomStringDisplay(
                    id = it.id,
                    value = it.value,
                    length = it.length,
                    created = it.created
                )
            }
        }
    }

    override suspend fun deleteRandomString(item: RandomStringEntity) {
        dao.deleteById(item.id)
    }

    override suspend fun insert(item: RandomStringForData) {
        dao.insert(item.toEntity())
    }

    override suspend fun deleteAllRandomStrings() {
        dao.deleteAll()
    }
    override suspend fun getRandomString(context: Context, maxLength: Int): RandomStringForData? {
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