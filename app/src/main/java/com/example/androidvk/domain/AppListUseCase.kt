package com.example.androidvk.domain

import jakarta.inject.Inject

class AppListUseCase @Inject constructor(private val applicationsRepository: ApplicationsRepository) {
    suspend operator fun invoke(): List<AppDetails> {
        val appList = this.applicationsRepository.getAppList();

        return appList.filter { app -> app.name.lowercase() !in Constants.DEPRECATED_APPS }
    }
}