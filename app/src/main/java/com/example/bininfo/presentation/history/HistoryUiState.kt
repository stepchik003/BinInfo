package com.example.bininfo.presentation.history

import com.example.bininfo.domain.model.BinInfo

sealed class HistoryUiState {
    object Loading : HistoryUiState()
    data class Success(val history: List<BinInfo>) : HistoryUiState()
    data class Error(val message: String) : HistoryUiState()
}