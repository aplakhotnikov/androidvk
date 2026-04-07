package com.example.androidvk

import com.example.androidvk.data.AppDetailsDto
import com.example.androidvk.data.AppDetailsMapperImpl
import com.example.androidvk.domain.AppDetails
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AppDetailsMapperTest {
    @Test
    fun `toDomain should map DTO to Domain correctly`() {
        val dto = AppDetailsDto(
            id = "aaaa-bbbb-cccc-1234",
            name = "Test App",
            description = "Test Description",
            category = "Tools",
            iconUrl = ""
        );

        val expected = AppDetails(
            ID = "aaaa-bbbb-cccc-1234",
            name = "Test App",
            description = "Test Description",
            category = "Tools",
            iconUrl = ""
        );

        val mapper = AppDetailsMapperImpl();

        assertEquals(mapper.toDomain(dto), expected);
    }
}