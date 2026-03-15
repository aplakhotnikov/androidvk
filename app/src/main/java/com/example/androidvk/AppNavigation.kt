package com.example.androidvk

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidvk.presentation.appdetails.AppDetailsScreen
import com.example.androidvk.presentation.applist.AppListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController=navController,
        startDestination = "app_list"
    ) {
        composable("app_list") {
            AppListScreen(onItemClick = { app -> navController.navigate("app_details/${app.ID}") })
        }

        composable("app_details/{appId}") { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId")?.toIntOrNull() ?: 0

            AppDetailsScreen(
                appId,
                onBackClick = {
                    navController.popBackStack();
                }
            );
        }
    }
}