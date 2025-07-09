package com.app.randomstringtask.presentations.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.app.randomString.viewmodel.SharedViewModel
import com.app.randomstringtask.presentations.navigation.AppNavigation
import com.app.randomstringtask.presentations.ui.theme.RandomStringTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomStringTaskTheme {
                AppNavigation(viewModel)
            }
        }
    }
}


