package com.example.androidvk

import androidx.annotation.DrawableRes

data class AppItem (
    val ID: Int,
    val name: String,
    val description: String,
    val category: String,
    @DrawableRes val iconRes: Int,
)

val appList = listOf(
    AppItem(
        ID = 1,
        name = "Сбербанк Online - с Салютом",
        description = "Больше чем банк",
        category = "Финансы",
        iconRes = R.drawable.ic_telegram
    ),
    AppItem(
        ID = 2,
        name = "Яндекс браузер - с Алисой",
        description = "Быстрый и безопасный браузер",
        category = "Инструменты",
        iconRes = R.drawable.ic_telegram
    ),
    AppItem(
        ID = 3,
        name = "Яндекс браузер - с Алисой",
        description = "Быстрый и безопасный браузер",
        category = "Инструменты",
        iconRes = R.drawable.ic_telegram
    ),
    AppItem(
        ID = 4,
        name = "Яндекс браузер - с Алисой",
        description = "Быстрый и безопасный браузер",
        category = "Инструменты",
        iconRes = R.drawable.ic_telegram
    ),
    AppItem(
        ID = 5,
        name = "Яндекс браузер - с Алисой",
        description = "Быстрый и безопасный браузер",
        category = "Инструменты",
        iconRes = R.drawable.ic_telegram
    ),
    AppItem(
        ID = 6,
        name = "Яндекс браузер - с Алисой",
        description = "Быстрый и безопасный браузер",
        category = "Инструменты",
        iconRes = R.drawable.ic_telegram
    )
)