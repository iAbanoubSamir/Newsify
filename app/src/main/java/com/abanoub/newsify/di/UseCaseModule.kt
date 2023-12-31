package com.abanoub.newsify.di

import com.abanoub.newsify.data.use_case.AddArticleToFavouriteUseCaseImpl
import com.abanoub.newsify.data.use_case.DeleteArticleFromFavouriteUseCaseImpl
import com.abanoub.newsify.data.use_case.GetFavouriteNewsUseCaseImpl
import com.abanoub.newsify.data.use_case.GetNewsUseCaseImpl
import com.abanoub.newsify.data.use_case.SearchNewsUseCaseImpl
import com.abanoub.newsify.domain.repository.FavouriteRepository
import com.abanoub.newsify.domain.repository.NewsRepository
import com.abanoub.newsify.domain.use_case.AddArticleToFavouriteUseCase
import com.abanoub.newsify.domain.use_case.DeleteArticleFromFavouriteUseCase
import com.abanoub.newsify.domain.use_case.GetFavouriteNewsUseCase
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

    @Provides
    @Singleton
    fun provideGetFavouriteNewsUseCase(favouriteRepository: FavouriteRepository): GetFavouriteNewsUseCase {
        return GetFavouriteNewsUseCaseImpl(favouriteRepository)
    }

    @Provides
    @Singleton
    fun provideAddArticleToFavouriteUseCase(favouriteRepository: FavouriteRepository): AddArticleToFavouriteUseCase {
        return AddArticleToFavouriteUseCaseImpl(favouriteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteArticleFromFavouriteUseCase(favouriteRepository: FavouriteRepository): DeleteArticleFromFavouriteUseCase {
        return DeleteArticleFromFavouriteUseCaseImpl(favouriteRepository)
    }
}