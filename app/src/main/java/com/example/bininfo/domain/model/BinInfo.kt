package com.example.bininfo.domain.model

data class BinInfo(
    val bin: String,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val country: String?,
    val latitude: Double?,
    val longitude: Double?,
    val bankName: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)
