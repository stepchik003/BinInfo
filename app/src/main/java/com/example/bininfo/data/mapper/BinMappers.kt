package com.example.bininfo.data.mapper

import com.example.bininfo.data.local.model.BinEntity
import com.example.bininfo.data.remote.model.BinInfoResponse
import com.example.bininfo.domain.model.BinInfo

fun BinInfoResponse.toDomain(bin: String): BinInfo {
    return BinInfo(
        bin = bin,
        scheme = scheme,
        type = type,
        brand = brand,
        country = country?.name,
        latitude = country?.latitude,
        longitude = country?.longitude,
        bankName = bank?.name,
        url = bank?.url,
        phone = bank?.phone,
        city = bank?.city
    )
}

fun BinInfoResponse.toEntity(bin: String): BinEntity {
    return BinEntity(
        bin = bin,
        scheme = scheme,
        type = type,
        brand = brand,
        country = country?.name,
        latitude = country?.latitude,
        longitude = country?.longitude,
        bankName = bank?.name,
        url = bank?.url,
        phone = bank?.phone,
        city = bank?.city
    )
}

fun BinEntity.toDomain(): BinInfo {
    return BinInfo(
        bin = bin,
        scheme = scheme,
        type = type,
        brand = brand,
        country = country,
        latitude = latitude,
        longitude = longitude,
        bankName = bankName,
        url = url,
        phone = phone,
        city = city
    )
}