package com.app.randomstringtask.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.app.randomString.viewmodel.RandomStringViewModel
import com.app.randomstringtask.presentations.ui.theme.RandomStringTaskTheme
import com.app.randomstringtask.presentations.screens.HomePage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RandomStringViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            RandomStringTaskTheme {
                HomePage(viewModel)
            }
        }

    }
}
