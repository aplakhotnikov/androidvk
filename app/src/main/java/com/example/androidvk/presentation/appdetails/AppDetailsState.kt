package com.example.androidvk.presentation.appdetails

import com.example.androidvk.data.AppInfo

sealed interface AppDetailsState {
    data object Loading: AppDetailsState;

    data class Error(
        val message: String = "Произошла ошибка"
    ): AppDetailsState;

    data class Content (
        val data: AppInfo
    ): AppDetailsState;
}