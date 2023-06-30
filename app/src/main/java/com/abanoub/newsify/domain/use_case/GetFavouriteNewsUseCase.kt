package com.abanoub.newsify.domain.use_case

import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetFavouriteNewsUseCase {

    suspend operator fun invoke(): Flow<Resource<List<Article>>>
}