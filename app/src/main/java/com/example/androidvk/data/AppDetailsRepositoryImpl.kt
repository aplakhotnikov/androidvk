package com.example.androidvk.data

import com.example.androidvk.data.local.AppDetailsDao
import com.example.androidvk.data.local.AppDetailsEntityMapper
import com.example.androidvk.domain.AppDetails
import com.example.androidvk.domain.AppDetailsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppDetailsRepositoryImpl @Inject constructor(
    private val applicationsApi: ApplicationsApi,
    private val appDetailsMapper: AppDetailsMapper,
    private val dao: AppDetailsDao,
    private val appDetailsEntityMapper: AppDetailsEntityMapper,
): AppDetailsRepository {
    override suspend fun getAppDetails(id: String): AppDetails? {
        return withContext(Dispatchers.IO) {
            val appDetailsEntity = dao.getAppDetails(id);

            if (appDetailsEntity != null) {
                appDetailsEntityMapper.toDomain(appDetailsEntity);
            } else {
                val dto = applicationsApi.getAppDetails(id);

                val appDetails = dto?.let { dto -> appDetailsMapper.toDomain(dto) }

                if (appDetails != null) {
                    dao.insertAppDetails(
                        appDetailsEntityMapper.toEntity(appDetails)
                    );
                }

                appDetails;
            }
        }
    }
}