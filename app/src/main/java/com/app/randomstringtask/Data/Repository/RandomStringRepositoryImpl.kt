package com.app.randomstringtask.Data.Repository

import com.app.randomstringtask.Data.local.RandomStringDao
import com.app.randomstringtask.Data.local.RandomStringEntity
import com.app.randomstringtask.Data.mapper.toEntity
import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import com.app.randomstringtask.Data.mapper.RandomStringForData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
}