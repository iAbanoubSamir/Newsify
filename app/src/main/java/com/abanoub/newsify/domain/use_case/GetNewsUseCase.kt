package com.abanoub.newsify.domain.use_case

import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetNewsUseCase {

    suspend operator fun invoke(countryCode: String, page: Int): Flow<Resource<List<Article>>>
}