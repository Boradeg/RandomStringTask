package com.app.randomstringtask.presentations.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.randomString.viewmodel.SharedViewModel
import com.app.randomstringtask.presentations.screens.HomePage
import com.app.randomstringtask.presentations.screens.SplashScreen

@Composable
fun AppNavigation(viewModel: SharedViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Home.route) {
            HomePage(viewModel, navController)
        }
    }
}