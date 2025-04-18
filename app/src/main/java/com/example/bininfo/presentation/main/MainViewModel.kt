package com.example.bininfo.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.domain.usecase.GetBinInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.bininfo.util.Result

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBinInfoUseCase: GetBinInfoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Idle)
    val uiState: StateFlow<MainUiState> = _uiState

    fun searchBin(bin: String) {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            when (val result = getBinInfoUseCase(bin)) {
                is Result.Success -> _uiState.value = MainUiState.Success(result.data)
                is Result.Error -> _uiState.value = MainUiState.Error(result.message)
            }
        }
    }
}