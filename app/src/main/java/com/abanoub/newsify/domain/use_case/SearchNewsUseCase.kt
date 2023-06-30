package com.abanoub.newsify.domain.use_case

import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchNewsUseCase {

    suspend operator fun invoke(query: String): Flow<Resource<List<Article>>>
}