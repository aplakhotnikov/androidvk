package com.example.androidvk.data

import kotlinx.serialization.Serializable

@Serializable
data class AppDetailsDto (
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String
);