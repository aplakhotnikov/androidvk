package com.example.androidvk.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidvk.domain.AppListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val appListUseCase: AppListUseCase
): ViewModel() {
    private val _state = MutableStateFlow<AppListState>(AppListState.Loading);
    private val _events = Channel<AppListScreenEvent>(Channel.BUFFERED);
    val state: StateFlow<AppListState> = _state.asStateFlow();
    val events = _events.receiveAsFlow();

    init {
        loadData();
    }

    private fun loadData() {
        viewModelScope.launch {
            runCatching {
                _state.value = AppListState.Loading;

                val appList = appListUseCase();

                _state.value = AppListState.Content(appList);
            }.onFailure {
                _state.value = AppListState.Error;
            }
        }
    }

    fun emitMessageEvent(msg: String) {
        viewModelScope.launch {
            _events.send(
                AppListScreenEvent.ShowSnackbar(msg)
            )
        }
    }
}