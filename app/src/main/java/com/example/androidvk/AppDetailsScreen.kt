package com.example.androidvk

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidvk.ui.theme.AndroidvkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(appID: Int, onBackClick: () -> Unit) {
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),

        topBar = {
            TopAppBar(title={}, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ), navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surface,
                    )
                }
            })
        }
    ) { contentPadding ->
        val app = appList.find { it -> it.ID == appID }

        Box(Modifier.padding(24.dp)) {
            Column(
                Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .padding(contentPadding)
            ) {
                Text(
                    text = stringResource(R.string.app_details_category),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = app?.category ?: "",
                    fontSize = 18.sp,
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.app_details_name), fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = app?.name ?: "",
                    fontSize = 18.sp,
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.app_details_description),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = app?.description ?: "",
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview()
@Composable()
private fun Preview() {
    AndroidvkTheme() {
        Surface() {
            AppDetailsScreen(1, {});
        }
    }
}