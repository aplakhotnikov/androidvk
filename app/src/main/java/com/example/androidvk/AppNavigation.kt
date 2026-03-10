package com.example.androidvk

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController=navController,
        startDestination = "app_list"
    ) {
        composable("app_list") {
            AppListScreen(onItemClick = {appId -> navController.navigate("app_detail/$appId")})
        }
    }
}