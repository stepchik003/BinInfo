package com.example.bininfo.data.remote

import com.example.bininfo.data.remote.model.BinInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApiService {
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinInfoResponse
}