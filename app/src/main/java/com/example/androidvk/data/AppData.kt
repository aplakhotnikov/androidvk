package com.example.androidvk.data

data class AppInfo (
    val ID: Int,
    val name: String,
    val description: String,
    val category: String,
)

val appList = listOf(
    AppInfo(
        ID = 1,
        name = "Сбербанк Online - с Салютом",
        description = "Больше чем банк",
        category = "Финансы",
    ),
    AppInfo(
        ID = 2,
        name = "Яндекс браузер - с Алисой",
        description = "Быстрый и безопасный браузер",
        category = "Инструменты",
    ),
    AppInfo(
        ID = 3,
        name = "Почта Mail.ru",
        description = "Почтовый агент для любых ящиков",
        category = "Инструменты",
    ),
    AppInfo(
        ID = 4,
        name = "Яндекс навигатор",
        description = "Парковки и заправки - по пути",
        category = "Транспорт",
    ),
    AppInfo(
        ID = 5,
        name = "Мой МТС",
        description = "Мой МТС - центр экосистемы МТС",
        category = "Инструменты",
    ),
    AppInfo(
        ID = 6,
        name = "Яндекс - с Алисой",
        description = "Яндекс - поиск всегда под рукой",
        category = "Инструменты",
    )
)