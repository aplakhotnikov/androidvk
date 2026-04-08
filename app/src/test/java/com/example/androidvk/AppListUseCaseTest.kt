package com.example.androidvk

import com.example.androidvk.domain.AppDetails
import com.example.androidvk.domain.AppListUseCase
import com.example.androidvk.domain.ApplicationsRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class AppListUseCaseTest {
    @Test
    fun`invoke should return all apps when none are deprecated`() {
        runBlocking {
            val applicationsRepository = mock<ApplicationsRepository>();
            val appListUseCase = AppListUseCase(applicationsRepository);
            val expected = listOf(
                AppDetails(
                    ID = "aaaa-bbbb-cccc-1234",
                    name = "Test App",
                    description = "Test Description",
                    category = "Tools",
                    iconUrl = ""
                )
            );

            whenever(applicationsRepository.getAppList()).thenReturn(expected);
            assertEquals(appListUseCase.invoke(), expected);
            verify(applicationsRepository).getAppList();
        }
    }

    @Test
    fun`invoke should filter out deprecated apps`() {
        runBlocking {
            val expected = listOf(
                AppDetails(
                    ID = "aaaa-bbbb-cccc-1234",
                    name = "Test App",
                    description = "Test Description",
                    category = "Tools",
                    iconUrl = ""
                )
            );

            val list = listOf(
                AppDetails(
                    ID = "aaaa-bbbb-cccc-1234",
                    name = "Test App",
                    description = "Test Description",
                    category = "Tools",
                    iconUrl = ""
                ),
                AppDetails(
                    ID = "aaaa-bbbb-cccc-1234",
                    name = "telegram",
                    description = "Test Description",
                    category = "Tools",
                    iconUrl = ""
                )
            );

            val applicationsRepository = mock<ApplicationsRepository>();
            val appListUseCase = AppListUseCase(applicationsRepository);

            whenever(applicationsRepository.getAppList()).thenReturn(list);

            assertEquals(appListUseCase.invoke(), expected);
            verify(applicationsRepository).getAppList();
        }
    }

    @Test
    fun`invoke should return empty list when all apps are deprecated`() {
        runBlocking {
            val list = listOf(
                AppDetails(
                    ID = "aaaa-bbbb-cccc-1234",
                    name = "telegram",
                    description = "Test Description",
                    category = "Tools",
                    iconUrl = ""
                )
            );

            val applicationsRepository = mock<ApplicationsRepository>();
            val appListUseCase = AppListUseCase(applicationsRepository);

            whenever(applicationsRepository.getAppList()).thenReturn(list);

            val result = appListUseCase.invoke();

            assertTrue(result.isEmpty());
            verify(applicationsRepository).getAppList();
        }
    }
}