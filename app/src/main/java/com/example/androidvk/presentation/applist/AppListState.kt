package com.example.androidvk.presentation.applist

import com.example.androidvk.domain.AppDetails

sealed interface AppListState {
    data object Loading: AppListState
    data object Error: AppListState
    data class Content(
        val data: List<AppDetails>
    ): AppListState
}