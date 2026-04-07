package com.example.androidvk

import com.example.androidvk.data.AppDetailsDto
import com.example.androidvk.data.AppDetailsMapper
import com.example.androidvk.data.ApplicationsApi
import com.example.androidvk.data.ApplicationsRepositoryImpl
import com.example.androidvk.domain.AppDetails
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class ApplicationsRepositoryTest {
    @Test
    fun `getAppList should return list of apps when API returns data`() {
        runBlocking {
            val testDto1 = AppDetailsDto(
                id = "1",
                name = "RuStore",
                description = "Магазин приложений",
                category = "Tools",
                iconUrl = ""
            );

            val testDto2 = AppDetailsDto(
                id = "2",
                name = "SberBank",
                description = "Банковское приложение",
                category = "Finance",
                iconUrl = ""
            );

            val testDomain1 = AppDetails(
                ID = "1",
                name = "RuStore",
                description = "Магазин приложений",
                category = "Tools",
                iconUrl = "",
                isInWishlist = false
            );

            val testDomain2 = AppDetails(
                ID = "2",
                name = "SberBank",
                description = "Банковское приложение",
                category = "Finance",
                iconUrl = "",
                isInWishlist = false
            );

            val dtoList = listOf(testDto1, testDto2);

            val applicationsApi = mock<ApplicationsApi>();
            val appDetailsMapper = mock<AppDetailsMapper>();

            whenever(applicationsApi.getAppList()).thenReturn(dtoList);
            whenever(appDetailsMapper.toDomain(testDto1)).thenReturn(testDomain1);
            whenever(appDetailsMapper.toDomain(testDto2)).thenReturn(testDomain2);

            val applicationsRepository = ApplicationsRepositoryImpl(
                applicationsApi,
                appDetailsMapper
            );

            val result = applicationsRepository.getAppList();

            assertEquals(2, result.size);
            assertEquals(testDomain1, result[0]);
            assertEquals(testDomain2, result[1]);
            verify(applicationsApi).getAppList();
            verify(appDetailsMapper, times(2)).toDomain(any());
        }
    }

    @Test
    fun `getAppList should return empty list when API returns empty list`() {
        runBlocking {
            val applicationsApi = mock<ApplicationsApi>();
            val appDetailsMapper = mock<AppDetailsMapper>();

            whenever(applicationsApi.getAppList()).thenReturn(emptyList());

            val applicationsRepository = ApplicationsRepositoryImpl(
                applicationsApi,
                appDetailsMapper
            );
            val result = applicationsRepository.getAppList();

            assertTrue(result.isEmpty());
            verify(applicationsApi).getAppList();
            verify(appDetailsMapper, never()).toDomain(any());
        }
    }
}