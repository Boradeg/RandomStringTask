package com.app.randomstringtask.domain.UseCases

import com.app.randomstringtask.domain.Repository.RandomStringRepository
import javax.inject.Inject

class DeleteAllRandomStringsUseCase @Inject constructor(private val repository: RandomStringRepository) {
    suspend operator fun invoke() {
        repository.deleteAllRandomStrings()
    }
}