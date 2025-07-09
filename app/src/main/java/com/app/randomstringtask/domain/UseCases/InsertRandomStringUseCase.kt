package com.app.randomstringtask.domain.UseCases

import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import com.app.randomstringtask.presentations.modal.RandomStringDisplayFetch
import javax.inject.Inject

class InsertRandomStringUseCase @Inject constructor(
    private val repository: RandomStringRepository
) {
    suspend operator fun invoke(item: RandomStringDisplayFetch) {
        repository.insert(item)
    }
}