package com.abanoub.newsify.di

import com.abanoub.newsify.data.use_case.GetNewsUseCaseImpl
import com.abanoub.newsify.data.use_case.SearchNewsUseCaseImpl
import com.abanoub.newsify.domain.repository.NewsRepository
import com.abanoub.newsify.domain.use_case.GetNewsUseCase
import com.abanoub.newsify.domain.use_case.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCaseImpl(newsRepository)
    }

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(newsRepository: NewsRepository): SearchNewsUseCase {
        return SearchNewsUseCaseImpl(newsRepository)
    }
}