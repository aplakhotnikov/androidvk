package com.example.androidvk.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ApplicationsApi {
    @GET("catalog")
    suspend fun getAppList(): List<AppDetailsDto>;
    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto?;
}