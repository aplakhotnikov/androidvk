package com.example.androidvk.data

import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class ApplicationsApi @Inject constructor() {
    suspend fun getAppList(): List<AppDetailsDto> {
        delay(5.seconds);

        return appList;
    }

    suspend fun getAppDetails(id: Int): AppDetailsDto? {
        delay(5.seconds);

        return appList.find { app -> app.ID == id};
    }
}