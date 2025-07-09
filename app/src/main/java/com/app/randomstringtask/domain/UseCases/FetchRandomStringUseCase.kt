package com.app.randomstringtask.domain.UseCases

import android.content.Context
import com.app.randomstringtask.Data.mapper.RandomStringForData
import com.app.randomstringtask.domain.Repository.RandomStringRepository
import javax.inject.Inject


class FetchRandomStringUseCase @Inject constructor(private val repository : RandomStringRepository){
    suspend operator fun invoke(context: Context, maxLength: Int): RandomStringForData? {
        return repository.getRandomString(context, maxLength)
    }
}

