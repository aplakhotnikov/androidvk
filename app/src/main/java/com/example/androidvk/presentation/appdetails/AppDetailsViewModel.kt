package com.example.androidvk.presentation.appdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import com.example.androidvk.domain.AppDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val appDetailsRepository: AppDetailsRepository,
    savedStateHandle: SavedStateHandle): ViewModel() {
    private val _id: String = savedStateHandle.get<String>("appId")
        ?: error("id must be provided")
    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading);

    val state: StateFlow<AppDetailsState> = this._state.asStateFlow();

    init {
        getData();
    }

    private fun getData() {
        viewModelScope.launch {
            runCatching {
                _state.value = AppDetailsState.Loading;

                val data = appDetailsRepository.getAppDetails(_id);

                if (data != null) {
                    _state.value = AppDetailsState.Content(data);
                } else {
                    _state.value = AppDetailsState.Error("Приложение с ID $_id не найдено")
                }
            }.onFailure { throwable ->
                Log.e("AppDetailsViewModel", "getData", throwable);
                _state.value = AppDetailsState.Error();
            }
        }
    }
}