package com.abanoub.newsify.data.repository

import com.abanoub.newsify.data.local.ArticleDao
import com.abanoub.newsify.data.mapper.ArticleMapper
import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.repository.FavouriteRepository
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val dao: ArticleDao,
    private val mapper: ArticleMapper
) : FavouriteRepository {

    override suspend fun getFavouriteNews(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading)
        try {
            val articles = withContext(Dispatchers.IO) {
                dao.getArticle()
            }
            emit(Resource.Success(articles.map { mapper.fromEntity(it) }))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

    override suspend fun addToFavourite(article: Article) {
        try {
            dao.insert(mapper.toEntity(article))
        } catch (_: Exception) {
        }
    }

    override suspend fun deleteFromFavourite(article: Article) {
        try {
            dao.delete(article.url.toString())
        } catch (_: Exception) {
        }
    }
}