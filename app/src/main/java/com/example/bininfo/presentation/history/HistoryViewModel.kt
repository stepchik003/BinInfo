package com.example.bininfo.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bininfo.domain.usecase.GetBinHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getBinHistoryUseCase: GetBinHistoryUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<HistoryUiState>(HistoryUiState.Loading)
    val uiState: StateFlow<HistoryUiState> = _uiState

    init {
        loadHistory()
    }

    fun loadHistory() {
        viewModelScope.launch {
            try {
                val history = getBinHistoryUseCase()
                _uiState.value = HistoryUiState.Success(history)
            } catch (e: Exception) {
                _uiState.value = HistoryUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

}