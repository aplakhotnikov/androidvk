package com.example.androidvk

import com.example.androidvk.data.local.AppDetailsEntity
import com.example.androidvk.data.local.AppDetailsEntityMapperImpl
import com.example.androidvk.domain.AppDetails
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AppDetailsEntityMapperTest {
    @Test
    fun `toDomain should map Entity to Domain correctly`() {
        val domain = AppDetails(
            ID = "aaaa-bbbb-cccc-1234",
            name = "Test App",
            description = "Test Description",
            category = "Tools",
            iconUrl = ""
        );

        val entity = AppDetailsEntity(
            id = "aaaa-bbbb-cccc-1234",
            name = "Test App",
            description = "Test Description",
            category = "Tools",
            iconUrl = "",
            isInWishlist = false
        );

        val mapper = AppDetailsEntityMapperImpl();

        assertEquals(mapper.toDomain(entity), domain);
    }

    @Test
    fun `toEntity should map Domain to Entity correctly`() {
        val domain = AppDetails(
            ID = "aaaa-bbbb-cccc-1234",
            name = "Test App",
            description = "Test Description",
            category = "Tools",
            iconUrl = ""
        );

        val entity = AppDetailsEntity(
            id = "aaaa-bbbb-cccc-1234",
            name = "Test App",
            description = "Test Description",
            category = "Tools",
            iconUrl = "",
            isInWishlist = false
        );

        val mapper = AppDetailsEntityMapperImpl();

        assertEquals(mapper.toEntity(domain), entity);
    }
}