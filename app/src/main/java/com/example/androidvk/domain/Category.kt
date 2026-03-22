package com.example.androidvk.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.androidvk.R

enum class Category(val labelResId: Int) {
    FINANCE(R.string.categories_finances),
    TOOLS(R.string.categories_tools),
    TRANSPORT(R.string.categories_transport);

    @Composable
    fun label(): String = stringResource(labelResId)
}