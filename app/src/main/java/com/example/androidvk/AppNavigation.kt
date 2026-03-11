package com.example.androidvk

import androidx.compose.runtime.Composable
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
            AppListScreen(onItemClick = {appId -> navController.navigate("app_details/$appId")})
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