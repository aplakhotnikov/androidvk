package com.example.androidvk

import com.example.androidvk.data.AppDetailsDto
import com.example.androidvk.data.AppDetailsMapper
import com.example.androidvk.data.AppDetailsRepositoryImpl
import com.example.androidvk.data.ApplicationsApi
import com.example.androidvk.data.local.AppDetailsDao
import com.example.androidvk.data.local.AppDetailsEntity
import com.example.androidvk.data.local.AppDetailsEntityMapper
import com.example.androidvk.domain.AppDetails
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class AppDetailsRepositoryTest {
    private val testId = "aaaa-bbbb-cccc-1234";

    private val testEntity = AppDetailsEntity(
        id = testId,
        name = "Test App",
        description = "Test Description",
        category = "Tools",
        iconUrl = "",
        isInWishlist = false
    );

    private val testDomain = AppDetails(
        ID = testId,
        name = "Test App",
        description = "Test Description",
        category = "Tools",
        iconUrl = "",
        isInWishlist = false
    );

    private val testDto = AppDetailsDto(
        id = testId,
        name = "Test App",
        description = "Test Description",
        category = "Tools",
        iconUrl = ""
    );

    private val applicationsApi = mock<ApplicationsApi>();
    private val appDetailsMapper = mock<AppDetailsMapper>();
    private val dao = mock<AppDetailsDao>();
    private val appDetailsEntityMapper = mock<AppDetailsEntityMapper>();
    private val appDetailsRepository = AppDetailsRepositoryImpl(applicationsApi,appDetailsMapper,dao, appDetailsEntityMapper);

    @Test
    fun `getAppDetails should return cached data`() {
        runBlocking {
            whenever(dao.getAppDetails(testId)).thenReturn(flowOf(testEntity));
            whenever(appDetailsEntityMapper.toDomain(testEntity)).thenReturn(testDomain);

            val result = appDetailsRepository.getAppDetails(testId).first();

            assertEquals(testDomain, result);
            verify(applicationsApi, never()).getAppDetails(any());
            verify(dao, never()).insertAppDetails(any());
        }
    }

    @Test
    fun `getAppDetails should fetch from network when cache is empty`() {
        runBlocking {
            whenever(dao.getAppDetails(testId)).thenReturn(flowOf(null));
            whenever(applicationsApi.getAppDetails(testId)).thenReturn(testDto);
            whenever(appDetailsMapper.toDomain(testDto)).thenReturn(testDomain);
            whenever(appDetailsEntityMapper.toEntity(testDomain)).thenReturn(testEntity);

            val result = appDetailsRepository.getAppDetails(testId).first();

            assertEquals(testDomain, result);
            verify(applicationsApi).getAppDetails(testId);
            verify(dao).insertAppDetails(testEntity);
        }
    }

    @Test
    fun `toggleWishlist should update status from false to true`() {
        runBlocking {
            whenever(dao.getAppDetails(testId)).thenReturn(flowOf(testEntity));

            appDetailsRepository.toggleWishlist(testId);
            verify(dao).updateWishlistStatus(testId, true);
        }
    }

    @Test
    fun `toggleWishlist should update status from true to false`() {
        runBlocking {
            val testEntity = testEntity.copy(isInWishlist = true);

            whenever(dao.getAppDetails(testId)).thenReturn(flowOf(testEntity));

            appDetailsRepository.toggleWishlist(testId);
            verify(dao).updateWishlistStatus(testId, false);
        }
    }
}