package com.example.androidvk.data

import com.example.androidvk.domain.AppDetails
import com.example.androidvk.domain.ApplicationsRepository

class ApplicationsRepositoryImpl(
    private val applicationsApi: ApplicationsApi,
    private val appDetailsMapper: AppDetailsMapper
): ApplicationsRepository {
    override suspend fun getAppList(): List<AppDetails> {
        val listDto = applicationsApi.getAppList();

        return listDto.map {dto ->  appDetailsMapper.toDomain(dto);};
    }

    override suspend fun getAppDetails(id: Int): AppDetails? {
        val dto = applicationsApi.getAppDetails(id);

        return dto?.let { dto -> appDetailsMapper.toDomain(dto) }
    }
}