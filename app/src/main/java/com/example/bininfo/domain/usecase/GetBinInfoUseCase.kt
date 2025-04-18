package com.example.bininfo.domain.usecase

import com.example.bininfo.domain.model.BinInfo
import com.example.bininfo.domain.repository.BinRepository
import com.example.bininfo.util.Result

class GetBinInfoUseCase(
    private val repository: BinRepository
) {
    suspend operator fun invoke(bin: String): Result<BinInfo> {
        return repository.getBinInfo(bin)
    }
}