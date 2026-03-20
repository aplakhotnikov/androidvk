package com.example.androidvk.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import com.example.androidvk.domain.ApplicationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val applicationsRepository: ApplicationsRepository,
    savedStateHandle: SavedStateHandle): ViewModel() {
    private val _id: Int = savedStateHandle.get<String>("appId")?.toIntOrNull()
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

                val data = applicationsRepository.getAppDetails(_id);

                if (data != null) {
                    _state.value = AppDetailsState.Content(data);
                } else {
                    _state.value = AppDetailsState.Error("Приложение с ID $_id не найдено")
                }
            }.onFailure {
                _state.value = AppDetailsState.Error();
            }
        }
    }
}