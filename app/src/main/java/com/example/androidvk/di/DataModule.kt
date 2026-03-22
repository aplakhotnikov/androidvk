package com.example.androidvk.di

import com.example.androidvk.data.ApplicationsRepositoryImpl
import com.example.androidvk.domain.ApplicationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindApplicationsRepository(
        impl: ApplicationsRepositoryImpl
    ): ApplicationsRepository;
}