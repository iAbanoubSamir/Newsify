package com.abanoub.newsify.di

import android.content.Context
import androidx.room.Room
import com.abanoub.newsify.data.local.ArticleDao
import com.abanoub.newsify.data.local.NewsDatabase
import com.abanoub.newsify.data.mapper.ArticleMapper
import com.abanoub.newsify.data.mapper.ArticleMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: NewsDatabase): ArticleDao {
        return database.articleDao()
    }

    @Provides
    @Singleton
    fun provideArticleMapper(): ArticleMapper {
        return ArticleMapperImpl()
    }
}