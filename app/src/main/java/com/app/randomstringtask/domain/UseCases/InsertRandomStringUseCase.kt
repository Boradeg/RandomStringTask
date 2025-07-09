package com.app.randomstringtask.domain.UseCases

import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.Data.mapper.RandomStringForData
import javax.inject.Inject

class InsertRandomStringUseCase @Inject constructor(
    private val repository: RandomStringRepository
) {
    suspend operator fun invoke(item: RandomStringForData) {
        repository.insert(item)
    }
}