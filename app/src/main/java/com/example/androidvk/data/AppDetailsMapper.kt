package com.example.androidvk.data

import com.example.androidvk.domain.AppDetails
import javax.inject.Inject
import javax.inject.Singleton

class AppDetailsMapper @Inject constructor(
    private val categoryMapper: CategoryMapper
) {
    fun toDomain(dto: AppDetailsDto): AppDetails {
        return AppDetails(
            ID = dto.ID,
            name = dto.name,
            description = dto.description,
            category = categoryMapper.toDomain(dto.category)
        );
    }
}