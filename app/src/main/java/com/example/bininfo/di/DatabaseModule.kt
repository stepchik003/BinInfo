package com.example.bininfo.di

import android.content.Context
import androidx.room.Room
import com.example.bininfo.data.local.BinDao
import com.example.bininfo.data.local.BinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BinDatabase =
        Room.databaseBuilder(
            context,
            BinDatabase::class.java,
            "bin_database"
        ).build()

    @Provides
    fun provideBinDao(database: BinDatabase): BinDao = database.binDao()
}