package com.example.androidvk.presentation

object Routes {
    const val AppList = "app_list";
    const val AppDetails = "app_details/{appId}";

    fun createAppDetails(appId: String): String = "app_details/$appId";
}