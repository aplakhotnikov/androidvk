package com.example.androidvk.presentation.applist

sealed interface AppListScreenEvent {
    data class ShowSnackbar(
        val msg: String
    ): AppListScreenEvent
}