package com.example.androidvk.domain

class AppListUseCase(private val applicationsRepository: ApplicationsRepository) {
    suspend operator fun invoke(): List<AppDetails> {
        val appList = this.applicationsRepository.getAppList();

        return appList.filter { app -> app.name.lowercase() !in Constants.DEPRECATED_APPS }
    }
}