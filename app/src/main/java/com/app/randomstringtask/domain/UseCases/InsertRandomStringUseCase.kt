package com.app.randomstringtask.domain.UseCases

import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import javax.inject.Inject

class InsertRandomStringUseCase @Inject constructor(
    private val repository: RandomStringRepository
) {
    suspend operator fun invoke(item: RandomStringDisplay) {
        repository.insert(item)
    }
}