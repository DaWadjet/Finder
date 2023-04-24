package com.example.finder.di

import com.example.finder.network.RandomuserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRandomuserService(): RandomuserService {
        return RandomuserService();
    }

}