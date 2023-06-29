package com.abanoub.newsify.di

import com.abanoub.newsify.data.local.ArticleDao
import com.abanoub.newsify.data.mapper.ArticleMapper
import com.abanoub.newsify.data.remote.NewsApiService
import com.abanoub.newsify.data.repository.FavouriteRepositoryImpl
import com.abanoub.newsify.data.repository.NewsRepositoryImpl
import com.abanoub.newsify.domain.repository.FavouriteRepository
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

    @Provides
    @Singleton
    fun provideFavouriteRepository(
        articleDao: ArticleDao,
        articleMapper: ArticleMapper
    ): FavouriteRepository {
        return FavouriteRepositoryImpl(articleDao, articleMapper)
    }
}