package com.app.randomstringtask.domain.Repository

import android.content.Context
import com.app.randomstringtask.Data.local.RandomStringEntity
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import com.app.randomstringtask.Data.mapper.RandomStringForData
import kotlinx.coroutines.flow.Flow

interface RandomStringRepository {
    fun observeAllRandomStrings(): Flow<List<RandomStringDisplay>>
    suspend fun insert(item: RandomStringForData)
    suspend fun deleteRandomString(item: RandomStringEntity)
    suspend fun deleteAllRandomStrings()
    suspend fun getRandomString(context: Context, maxLength: Int): RandomStringForData?
}