package com.example.androidvk.domain

interface ApplicationsRepository {
    suspend fun getAppList(): List<AppDetails>;
}