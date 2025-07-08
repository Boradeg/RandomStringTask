package com.app.randomstringtask.ui.theme.Data.Repository

import com.app.randomstringtask.ui.theme.Data.RandomStringDao
import com.app.randomstringtask.ui.theme.Data.RandomStringEntity
import com.app.randomstringtask.ui.theme.Data.toEntity
import com.app.randomstringtask.ui.theme.Screen.RandomStringDisplay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RandomStringRepository @Inject constructor(
    private val dao: RandomStringDao
) {

    // Get Flow of all saved strings from Room
    fun observeAllRandomStrings(): Flow<List<RandomStringDisplay>> {
        return dao.observeAllRandomStrings().map { list ->
            list.map {
                RandomStringDisplay(
                    value = it.value,
                    length = it.length,
                    created = it.created
                )
            }
        }
    }
    suspend fun insert(item: RandomStringDisplay) {
        dao.insert(item.toEntity()) // Convert to DB entity if needed
    }

    // Delete one random string
    suspend fun deleteRandomString(item: RandomStringEntity) {
        dao.deleteRandomString(
            RandomStringEntity(
                value = item.value,
                length = item.length,
                created = item.created
            )
        )
    }

    // Delete all strings
    suspend fun deleteAllRandomStrings() {
        dao.deleteAll()
    }
}