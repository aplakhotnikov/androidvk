package com.example.androidvk.data

import com.example.androidvk.domain.AppDetails
import javax.inject.Inject

interface AppDetailsMapper {
    fun toDomain(dto: AppDetailsDto): AppDetails;
}


class AppDetailsMapperImpl @Inject constructor(
): AppDetailsMapper {
    override fun toDomain(dto: AppDetailsDto): AppDetails {
        return AppDetails(
            ID = dto.id,
            name = dto.name,
            description = dto.description,
            category = dto.category,
            iconUrl = dto.iconUrl,
        );
    }
}