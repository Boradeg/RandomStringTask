package com.app.randomstringtask.domain.UseCases

import com.app.randomstringtask.Data.local.RandomStringEntity
import com.app.randomstringtask.domain.Repository.RandomStringRepository
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ObserveRandomStringsUseCase @Inject constructor(
    private val repository: RandomStringRepository
) {
    operator fun invoke(): Flow<List<RandomStringDisplay>> = repository.observeAllRandomStrings()
}



