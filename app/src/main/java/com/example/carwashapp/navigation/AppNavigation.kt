package com.example.carwashapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carwashapp.view.AnimatedSplashScreen
import com.example.carwashapp.view.HomeScreen
import com.example.carwashapp.view.services.ServiceFormScreen
import com.example.carwashapp.view.services.ServiceListScreen
import com.example.carwashapp.view_model.ServiceDetailViewModel
import com.example.carwashapp.view_model.ServiceListViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {

        composable(route = AppScreens.SplashScreen.route){
            AnimatedSplashScreen(navController)
        }

        composable(route = AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }

        composable(route = AppScreens.ServiceDetail.route+"?serviceId={serviceId}"){
            val viewModel: ServiceDetailViewModel = hiltViewModel()
            val state = viewModel.state.value
            ServiceFormScreen(navController, state, viewModel::addNewService, viewModel::updateService)
        }

        composable(route = AppScreens.ServiceList.route){
            val viewModel: ServiceListViewModel = hiltViewModel()
            val state = viewModel.state.value
            val isRefreshing = viewModel.isRefreshing.collectAsState()
            ServiceListScreen(navController, state, isRefreshing.value, viewModel::getServiceList, onItemSelect = {serviceId -> navController.navigate(AppScreens.ServiceDetail.route + "?serviceId=$serviceId")}, deleteService = viewModel::deleteService)
        }
    }
}