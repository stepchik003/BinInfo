package com.example.bininfo.data.repository

import com.example.bininfo.data.local.BinDao
import com.example.bininfo.data.mapper.toDomain
import com.example.bininfo.data.mapper.toEntity
import com.example.bininfo.data.remote.BinApiService
import com.example.bininfo.domain.model.BinInfo
import com.example.bininfo.domain.repository.BinRepository
import javax.inject.Inject
import com.example.bininfo.util.Result

class BinRepositoryImpl @Inject constructor(
    private val api: BinApiService,
    private val dao: BinDao
) : BinRepository {

    override suspend fun getBinInfo(bin: String): Result<BinInfo> {
        if (bin.length !in 6..8) return Result.Error("Введите 6-8 цифр!")
        return try {
            val response = api.getBinInfo(bin)
            val entity = response.toEntity(bin)
            dao.insert(entity)
            Result.Success(response.toDomain(bin))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getBinHistory(): List<BinInfo> {
        val result = dao.getAll().map { it.toDomain() }.toMutableList()
        return result
    }
}