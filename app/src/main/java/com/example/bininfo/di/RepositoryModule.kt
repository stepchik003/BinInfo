package com.example.bininfo.di

import com.example.bininfo.data.local.BinDao
import com.example.bininfo.data.remote.BinApiService
import com.example.bininfo.data.repository.BinRepositoryImpl
import com.example.bininfo.domain.repository.BinRepository
import com.example.bininfo.domain.usecase.GetBinHistoryUseCase
import com.example.bininfo.domain.usecase.GetBinInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBinRepository(
        api: BinApiService,
        dao: BinDao
    ): BinRepository {
        return BinRepositoryImpl(api, dao)
    }

    @Provides
    fun provideGetBinInfoUseCase(repository: BinRepository): GetBinInfoUseCase {
        return GetBinInfoUseCase(repository)
    }

    @Provides
    fun provideGetBinHistoryUseCase(repository: BinRepository): GetBinHistoryUseCase {
        return GetBinHistoryUseCase(repository)
    }
}