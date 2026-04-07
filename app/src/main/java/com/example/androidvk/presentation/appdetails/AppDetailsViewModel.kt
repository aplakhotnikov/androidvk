package com.example.androidvk.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import com.example.androidvk.domain.AppDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
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
        observeData();
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            appDetailsRepository.toggleWishlist(_id);
        }
    }

    private fun observeData() {
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading;

            appDetailsRepository.getAppDetails(_id)
                .catch {
                    _state.value = AppDetailsState.Error();
                }
                .collect { data ->
                    if (data != null) {
                        _state.value = AppDetailsState.Content(data);
                    } else {
                        _state.value = AppDetailsState.Error("Приложение с ID $_id не найдено")
                    }
                }
        }
    }
}