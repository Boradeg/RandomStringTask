package com.app.randomstringtask.domain.UseCases

import android.content.Context
import com.app.randomstringtask.Data.provider.RandomStringProvider
import com.app.randomstringtask.Data.mapper.RandomStringForData
import javax.inject.Inject


class FetchRandomStringUseCase @Inject constructor(){
    operator fun invoke(context: Context, maxLength: Int): RandomStringForData? {
        return RandomStringProvider.getRandomString(context, maxLength)
    }
}

