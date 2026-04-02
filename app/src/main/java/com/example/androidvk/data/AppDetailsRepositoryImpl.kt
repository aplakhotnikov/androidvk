package com.example.androidvk.data

import com.example.androidvk.data.local.AppDetailsDao
import com.example.androidvk.data.local.AppDetailsEntityMapper
import com.example.androidvk.domain.AppDetails
import com.example.androidvk.domain.AppDetailsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AppDetailsRepositoryImpl @Inject constructor(
    private val applicationsApi: ApplicationsApi,
    private val appDetailsMapper: AppDetailsMapper,
    private val dao: AppDetailsDao,
    private val appDetailsEntityMapper: AppDetailsEntityMapper,
): AppDetailsRepository {
    override fun getAppDetails(id: String): Flow<AppDetails?> {
        return dao.getAppDetails(id).map {
            appDetailsEntity ->

            if (appDetailsEntity != null) {
                appDetailsEntityMapper.toDomain(appDetailsEntity);
            } else {
                val dto = applicationsApi.getAppDetails(id);

                val appDetails = dto?.let { dto -> appDetailsMapper.toDomain(dto) }

                if (appDetails != null) {
                    withContext(Dispatchers.IO) {
                        dao.insertAppDetails(
                            appDetailsEntityMapper.toEntity(appDetails)
                        );
                    }
                }

                appDetails;
            }
        }
    }

    override suspend fun toggleWishlist(id: String) {
        withContext(Dispatchers.IO) {
            val currData = dao.getAppDetails(id).first();

            currData?.let {
                dao.updateWishlistStatus(id, !it.isInWishlist);
            }
        }
    }
}