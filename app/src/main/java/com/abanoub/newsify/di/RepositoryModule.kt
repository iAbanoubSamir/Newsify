package com.abanoub.newsify.di

import com.abanoub.newsify.data.remote.NewsApiService
import com.abanoub.newsify.data.repository.NewsRepositoryImpl
import com.abanoub.newsify.domain.repository.NewsRepository
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
    fun provideNewsRepository(newsApiService: NewsApiService): NewsRepository {
        return NewsRepositoryImpl(newsApiService)
    }
}