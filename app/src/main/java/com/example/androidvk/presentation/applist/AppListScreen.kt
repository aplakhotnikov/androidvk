package com.example.androidvk.presentation.applist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidvk.domain.AppDetails
import com.example.androidvk.ui.theme.AndroidvkTheme

@Composable
fun AppListScreen(onItemClick: (AppDetails) -> Unit) {
    val viewModel = hiltViewModel<AppListViewModel>();
    val state by viewModel.state.collectAsStateWithLifecycle();

    when(val currState = state) {
        is AppListState.Content -> {
            AppListContent(currState.data, onItemClick);
        }

        is AppListState.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator();
            }
        }

        is AppListState.Error -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Ошибка");
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppListContent(appList: List<AppDetails>, onItemClick: (AppDetails) -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val viewModel = viewModel<AppListViewModel>();

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect {
                event ->
            when (event) {
                is AppListScreenEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.msg);
                }
            }

        }
    }

    Scaffold(
        snackbarHost = {SnackbarHost(snackbarHostState)},
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(16.dp),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = MaterialTheme.colorScheme.surface
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.List,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = MaterialTheme.colorScheme.surface
                    )
                },
                title = {Text(text="RuStore", color= MaterialTheme.colorScheme.surface)},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        }

    ) { paddingValues ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clip(RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                )),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = appList,
                    key = { it.ID }
                ) { app ->
                    AppListItem(app, onItemClick, onLogoClick = {
                        onItemLogoClickHandle(app, {msg -> viewModel.emitMessageEvent(msg)});
                    });
                }
            }
        }
    }
}

private fun onItemLogoClickHandle(app: AppDetails, emitMessageEvent: (msg: String) -> Unit) {
    emitMessageEvent("Click по logo приложения ${app.name}")
}

@Preview()
@Composable()
private fun Preview() {
    AndroidvkTheme() {
        Surface() {
            AppListContent(
                appList=listOf(
                    AppDetails("1","name","", "Развлечения", ""),
                    AppDetails("2","name","", "Развлечения", ""),
                    AppDetails("3","name","", "Развлечения", "")),
                {}
            )
        }
    }
}