package com.example.bininfo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bininfo.data.local.model.BinEntity

@Database(entities = [BinEntity::class], version = 1)
abstract class BinDatabase : RoomDatabase() {
    abstract fun binDao(): BinDao
}