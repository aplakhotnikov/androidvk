package com.example.androidvk

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidvk.domain.Routes
import com.example.androidvk.presentation.appdetails.AppDetailsScreen
import com.example.androidvk.presentation.applist.AppListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController=navController,
        startDestination = Routes.AppList
    ) {
        composable(Routes.AppList) {
            AppListScreen(onItemClick = {
                app -> navController.navigate(Routes.createAppDetails(app.ID))
            })
        }

        composable(Routes.AppDetails) { backStackEntry ->
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