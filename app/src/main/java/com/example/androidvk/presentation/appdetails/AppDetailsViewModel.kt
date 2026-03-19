package com.example.androidvk.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import com.example.androidvk.data.AppDetailsMapper
import com.example.androidvk.data.ApplicationsApi
import com.example.androidvk.data.ApplicationsRepositoryImpl
import com.example.androidvk.data.CategoryMapper
import com.example.androidvk.domain.ApplicationsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppDetailsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val applicationsRepository: ApplicationsRepository = ApplicationsRepositoryImpl(
        ApplicationsApi(),
        AppDetailsMapper(
            CategoryMapper()
        ),
    );
    private val _id: Int = savedStateHandle.get<Int>("id")
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

    companion object {
        fun provideFactory(id: Int): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AppDetailsViewModel(
                        savedStateHandle = SavedStateHandle(
                            mapOf("id" to id)
                        )
                    ) as T
                }
            }
        }
    }
}