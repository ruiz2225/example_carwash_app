package com.example.carwashapp.navigation

import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class AppScreens(val route: String, val arguments: List<NamedNavArgument>){
    object HomeScreen: AppScreens("home_screen", emptyList())
    object ServiceList: AppScreens("service_list", emptyList())
    object ServiceDetail: AppScreens(
        route = "service_detail",
        arguments = listOf(
            navArgument("serviceId"){nullable = true}
        ))
}
