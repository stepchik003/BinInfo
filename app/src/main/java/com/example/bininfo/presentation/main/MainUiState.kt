package com.example.bininfo.presentation.main

import com.example.bininfo.domain.model.BinInfo

sealed class MainUiState {
    object Idle : MainUiState()
    object Loading : MainUiState()
    data class Success(val binInfo: BinInfo) : MainUiState()
    data class Error(val message: String) : MainUiState()
}