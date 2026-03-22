package com.example.androidvk.presentation

object Routes {
    val AppList = "app_list";
    val AppDetails = "app_details/{appId}";

    fun createAppDetails(appId: Int): String = "app_details/$appId";
}