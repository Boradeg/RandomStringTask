package com.app.randomstringtask.ui.theme.UseCases

import android.content.Context
import com.app.randomstringtask.ui.theme.Repository.RandomStringProvider
import com.app.randomstringtask.ui.theme.Screen.RandomStringDisplay
import javax.inject.Inject

class FetchRandomStringUseCase @Inject constructor(){
    operator fun invoke(context: Context, maxLength: Int): RandomStringDisplay? {
        return RandomStringProvider.getRandomString(context, maxLength)
    }
}