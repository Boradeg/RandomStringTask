package com.app.randomstringtask.domain.UseCases

import android.content.Context
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import com.app.randomstringtask.Data.provider.RandomStringProvider
import com.app.randomstringtask.presentations.modal.RandomStringDisplayFetch
import javax.inject.Inject


class FetchRandomStringUseCase @Inject constructor(){
    operator fun invoke(context: Context, maxLength: Int): RandomStringDisplayFetch? {
        return RandomStringProvider.getRandomString(context, maxLength)
    }
}

