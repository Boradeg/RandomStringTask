package com.app.randomstringtask.ui.theme.UseCases

import android.content.Context
import com.app.randomstringtask.ui.theme.Data.Repository.RandomStringRepository
import com.app.randomstringtask.ui.theme.Data.toEntity
import com.app.randomstringtask.ui.theme.Repository.RandomStringProvider
import com.app.randomstringtask.ui.theme.Screen.RandomStringDisplay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ObserveRandomStringsUseCase @Inject constructor(
    private val repository: RandomStringRepository
) {
    operator fun invoke(): Flow<List<RandomStringDisplay>> {
        return repository.observeAllRandomStrings()
    }
}
class FetchRandomStringUseCase @Inject constructor(){
    operator fun invoke(context: Context, maxLength: Int): RandomStringDisplay? {
        return RandomStringProvider.getRandomString(context, maxLength)
    }
}
class InsertRandomStringUseCase @Inject constructor(
    private val repository: RandomStringRepository
) {
    suspend operator fun invoke(item: RandomStringDisplay) {
        repository.insert(item)
    }
}

class DeleteRandomStringUseCase @Inject constructor(private val repository: RandomStringRepository) {
    suspend operator fun invoke(item: RandomStringDisplay) {
        repository.deleteRandomString(item.toEntity())
    }
}

class DeleteAllRandomStringsUseCase @Inject constructor(private val repository: RandomStringRepository) {
    suspend operator fun invoke() {
        repository.deleteAllRandomStrings()
    }
}