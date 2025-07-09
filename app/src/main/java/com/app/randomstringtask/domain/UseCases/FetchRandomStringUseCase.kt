package com.app.randomstringtask.domain.UseCases

import android.content.Context
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import com.app.randomstringtask.Data.provider.RandomStringProvider
import javax.inject.Inject


class FetchRandomStringUseCase @Inject constructor(){
    operator fun invoke(context: Context, maxLength: Int): RandomStringDisplay? {
        return RandomStringProvider.getRandomString(context, maxLength)
    }
}

