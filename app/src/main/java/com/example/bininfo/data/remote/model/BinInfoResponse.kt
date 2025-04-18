package com.example.bininfo.data.remote.model

data class BinInfoResponse(
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val country: CountryResponse?,
    val bank: BankResponse?
)

data class CountryResponse(
    val name: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class BankResponse(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)