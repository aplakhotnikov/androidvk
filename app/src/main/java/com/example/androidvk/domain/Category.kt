package com.example.androidvk.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Category(val label: String) {
    @SerialName("Приложения")
    APP("Приложения"),

    @SerialName("Игры")
    GAME("Игры"),

    @SerialName("Производительность")
    PRODUCTIVITY("Производительность"),

    @SerialName("Социальные сети")
    SOCIAL("Социальные сети"),

    @SerialName("Образование")
    EDUCATION("Образование"),

    @SerialName("Развлечения")
    ENTERTAINMENT("Развлечения"),

    @SerialName("Музыка")
    MUSIC("Музыка"),

    @SerialName("Видео")
    VIDEO("Видео"),

    @SerialName("Фотография")
    PHOTOGRAPHY("Фотография"),

    @SerialName("Здоровье")
    HEALTH("Здоровье"),

    @SerialName("Спорт")
    SPORTS("Спорт"),

    @SerialName("Новости")
    NEWS("Новости"),

    @SerialName("Книги")
    BOOKS("Книги"),

    @SerialName("Бизнес")
    BUSINESS("Бизнес"),

    @SerialName("Финансы")
    FINANCE("Финансы"),

    @SerialName("Путешествия")
    TRAVEL("Путешествия"),

    @SerialName("Карты")
    MAPS("Карты"),

    @SerialName("Еда")
    FOOD("Еда"),

    @SerialName("Покупки")
    SHOPPING("Покупки"),

    @SerialName("Здоровье и фитнес")
    FITNESS("Здоровье и фитнес"),

    @SerialName("Фото и видео")
    MEDIA("Фото и видео"),

    @SerialName("Еда и напитки")
    FOOD_AND_DRINKS("Еда и напитки"),

    @SerialName("Утилиты")
    UTILITIES("Утилиты");


    fun label(): String = label;
}