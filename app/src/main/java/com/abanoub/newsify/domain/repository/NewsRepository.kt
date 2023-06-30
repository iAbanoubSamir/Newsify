package com.abanoub.newsify.domain.repository

import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(countryCode: String): Flow<Resource<List<Article>>>

    suspend fun searchNews(query: String): Flow<Resource<List<Article>>>
}