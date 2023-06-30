package com.abanoub.newsify.data.repository

import com.abanoub.newsify.data.remote.NewsApiService
import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.repository.NewsRepository
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService
) : NewsRepository {

    override suspend fun getNews(countryCode: String):
            Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading)
        try {
            val response = withContext(Dispatchers.IO) {
                api.getNews(countryCode)
            }
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.articles))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

    override suspend fun searchNews(query: String):
            Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading)
        try {
            val response = withContext(Dispatchers.IO) {
                api.searchNews(query)
            }
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.articles))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }
}