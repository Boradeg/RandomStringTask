package com.app.randomstringtask.domain.UseCases

import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRandomStringsUseCase @Inject constructor(
    private val repository: RandomStringRepository
) {
    operator fun invoke(): Flow<List<RandomStringDisplay>> {
        return repository.observeAllRandomStrings()
    }
}