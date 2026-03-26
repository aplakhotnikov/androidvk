package com.example.androidvk.presentation.appdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.androidvk.R
import com.example.androidvk.domain.AppDetails
import com.example.androidvk.ui.theme.AndroidvkTheme
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(onBackClick: () -> Unit) {
    val viewModel = hiltViewModel<AppDetailsViewModel>();
    val state by viewModel.state.collectAsStateWithLifecycle();

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
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
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .clip(RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                ))) {
                    when (val currState = state) {
                        is AppDetailsState.Loading -> {
                            Box(
                                Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator();
                            }
                        }

                        is AppDetailsState.Error -> {
                            Box(
                                Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(currState.message);
                            }
                        }

                        is AppDetailsState.Content -> {
                            AppDetailsContent(currState.data);
                        }
                    }
                }
    }
}

@Composable
fun AppDetailsContent(app: AppDetails) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            AsyncImage(
                model = app.iconUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp))
            );
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.app_details_category),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = app.category,
                fontSize = 18.sp,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.app_details_name), fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = app.name,
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
                text = app.description,
                fontSize = 18.sp
            )
        }

}

@Preview()
@Composable()
private fun Preview() {
    AndroidvkTheme() {
        Surface {
            AppDetailsContent(
                AppDetails(
                    "1",
                    "name",
                    "description",
                    "Развлечения",
                    ""
                )
            );
        }
    }
}