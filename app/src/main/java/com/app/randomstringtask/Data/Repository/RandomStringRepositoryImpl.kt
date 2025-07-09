package com.app.randomstringtask.Data.Repository

import com.app.randomstringtask.Data.local.RandomStringDao
import com.app.randomstringtask.Data.local.RandomStringEntity
import com.app.randomstringtask.Data.mapper.toEntity
import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
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
                    value = it.value,
                    length = it.length,
                    created = it.created
                )
            }
        }
    }

    override suspend fun insert(item: RandomStringDisplay) {
        dao.insert(item.toEntity())
    }

    override suspend fun deleteRandomString(item: RandomStringEntity) {
        dao.deleteRandomString(
            RandomStringEntity(
                value = item.value,
                length = item.length,
                created = item.created
            )
        )
    }

    override suspend fun deleteAllRandomStrings() {
        dao.deleteAll()
    }
}