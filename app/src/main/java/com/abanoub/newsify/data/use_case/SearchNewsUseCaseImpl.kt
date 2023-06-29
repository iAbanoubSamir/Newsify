package com.abanoub.newsify.data.use_case

import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.repository.NewsRepository
import com.abanoub.newsify.domain.use_case.SearchNewsUseCase
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCaseImpl(
    private val repository: NewsRepository
) : SearchNewsUseCase {

    override suspend fun invoke(query: String, page: Int): Flow<Resource<List<Article>>> {
        return repository.searchNews(query, page)
    }
}