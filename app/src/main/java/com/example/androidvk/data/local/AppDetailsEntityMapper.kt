package com.example.androidvk.data.local

import com.example.androidvk.domain.AppDetails

interface AppDetailsEntityMapper {
    fun toEntity(domain: AppDetails): AppDetailsEntity;
    fun toDomain(entity: AppDetailsEntity): AppDetails
}

class AppDetailsEntityMapperImpl:  AppDetailsEntityMapper{
    override fun toEntity(domain: AppDetails): AppDetailsEntity {
        return AppDetailsEntity(
            id = domain.ID,
            name = domain.name,
            description = domain.description,
            category = domain.category,
            iconUrl = domain.iconUrl,
            isInWishlist = domain.isInWishlist
        );
    }

    override fun toDomain(entity: AppDetailsEntity): AppDetails {
        return AppDetails(
            ID = entity.id,
            name = entity.name,
            description = entity.description,
            category = entity.category,
            iconUrl = entity.iconUrl,
            isInWishlist = entity.isInWishlist,
        );
    }
}