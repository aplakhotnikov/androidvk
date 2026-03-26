package com.example.androidvk.domain

interface ApplicationsRepository {
    suspend fun getAppList(): List<AppDetails>;
    suspend fun getAppDetails(id: String): AppDetails?;
}