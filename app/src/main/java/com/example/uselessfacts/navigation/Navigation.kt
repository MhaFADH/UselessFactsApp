package com.example.uselessfacts.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uselessfacts.model.RandomFactViewModel
import com.example.uselessfacts.ui.screens.FactScreen
import com.example.uselessfacts.ui.screens.HistoryScreen


@Composable
fun AppNavigation() {
    val navHostController : NavHostController = rememberNavController()
    val factViewModel : RandomFactViewModel = viewModel()

    NavHost(navController = navHostController, startDestination = Routes.FactScreen.route) {
        composable(Routes.FactScreen.route) {
            FactScreen(navHostController,factViewModel)
        }
        composable(Routes.HistoryScreen.route) {
            HistoryScreen(navHostController,factViewModel)
        }
    }
}
