package com.abanoub.newsify.data.use_case

import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.repository.FavouriteRepository
import com.abanoub.newsify.domain.use_case.GetFavouriteNewsUseCase
import com.abanoub.newsify.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteNewsUseCaseImpl @Inject constructor(
    private val repository: FavouriteRepository
) : GetFavouriteNewsUseCase {

    override suspend fun invoke(): Flow<Resource<List<Article>>> {
        return repository.getFavouriteNews()
    }
}