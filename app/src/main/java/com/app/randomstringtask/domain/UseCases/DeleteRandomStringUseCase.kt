package com.app.randomstringtask.domain.UseCases

import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.Data.mapper.toEntity
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import javax.inject.Inject

class DeleteRandomStringUseCase @Inject constructor(private val repository: RandomStringRepository) {
    suspend operator fun invoke(item: RandomStringDisplay) {
        repository.deleteRandomString(item.toEntity())
    }
}