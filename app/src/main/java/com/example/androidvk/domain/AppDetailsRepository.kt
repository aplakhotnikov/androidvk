package com.example.androidvk.domain

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): AppDetails?;
}