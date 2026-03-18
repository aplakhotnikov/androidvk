package com.example.androidvk

import androidx.annotation.DrawableRes

data class AppItem (
    val ID: Int,
    val name: String,
    val description: String,
    val category: String,
)

val appList = listOf(
    AppItem(
        ID = 1,
        name = "Сбербанк Online - с Салютом",
        description = "Больше чем банк",
        category = "Финансы",
    ),
    AppItem(
        ID = 2,
        name = "Яндекс браузер - с Алисой",
        description = "Быстрый и безопасный браузер",
        category = "Инструменты",
    ),
    AppItem(
        ID = 3,
        name = "Почта Mail.ru",
        description = "Почтовый агент для любых ящиков",
        category = "Инструменты",
    ),
    AppItem(
        ID = 4,
        name = "Яндекс навигатор",
        description = "Парковки и заправки - по пути",
        category = "Транспорт",
    ),
    AppItem(
        ID = 5,
        name = "Мой МТС",
        description = "Мой МТС - центр экосистемы МТС",
        category = "Инструменты",
    ),
    AppItem(
        ID = 6,
        name = "Яндекс - с Алисой",
        description = "Яндекс - поиск всегда под рукой",
        category = "Инструменты",
    )
)