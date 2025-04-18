package com.example.bininfo.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_history")
data class BinEntity(
    @PrimaryKey val bin: String,
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
