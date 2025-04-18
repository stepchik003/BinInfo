package com.example.bininfo.domain.usecase

import com.example.bininfo.domain.model.BinInfo
import com.example.bininfo.domain.repository.BinRepository

class GetBinHistoryUseCase(
    private val repository: BinRepository
) {
    suspend operator fun invoke(): List<BinInfo> {
        return repository.getBinHistory()
    }
}