package com.example.androidvk.domain

data class AppDetails (
    val ID: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
    val isInWishlist: Boolean = false
)