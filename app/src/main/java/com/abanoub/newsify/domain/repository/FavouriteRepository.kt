package com.abanoub.newsify.domain.repository

import com.abanoub.newsify.data.local.ArticleEntity
import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    suspend fun getFavouriteNews(): Flow<Resource<List<Article>>>

    suspend fun addToFavourite(article: Article)

    suspend fun deleteFromFavourite(article: Article)
}