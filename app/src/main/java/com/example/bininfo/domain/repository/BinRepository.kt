package com.example.bininfo.domain.repository

import com.example.bininfo.domain.model.BinInfo
import com.example.bininfo.util.Result

interface BinRepository {
    suspend fun getBinInfo(bin: String): Result<BinInfo>
    suspend fun getBinHistory(): List<BinInfo>
}