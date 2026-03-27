package com.example.androidvk.data

import com.example.androidvk.domain.Category
import javax.inject.Inject
import javax.inject.Singleton

class CategoryMapper @Inject constructor() {
    fun toDomain(category: String): Category {
        return when (category.lowercase()) {
            "финансы" -> Category.FINANCE
            "инструменты" -> Category.TOOLS
            "транспорт" -> Category.TRANSPORT
            else -> throw IllegalStateException("unsupported category type: $category")
        }
    }
}