package com.example.bininfo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bininfo.data.local.model.BinEntity

@Dao
interface BinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bin: BinEntity)

    @Query("SELECT * FROM bin_history")
    suspend fun getAll(): List<BinEntity>
}