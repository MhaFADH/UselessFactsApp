package com.example.uselessfacts.navigation

sealed class Routes(val route: String) {
    object FactScreen : Routes("factScreen")

    object HistoryScreen : Routes("historyScreen")
}