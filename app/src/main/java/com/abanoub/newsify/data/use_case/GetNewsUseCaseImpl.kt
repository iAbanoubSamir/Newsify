package com.abanoub.newsify.data.use_case

import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.repository.NewsRepository
import com.abanoub.newsify.domain.use_case.GetNewsUseCase
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : GetNewsUseCase {

    override suspend fun invoke(countryCode: String, page: Int): Flow<Resource<List<Article>>> {
        return repository.getNews(countryCode, page)
    }
}