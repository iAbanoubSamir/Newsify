package com.abanoub.newsify.data.use_case

import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.repository.FavouriteRepository
import com.abanoub.newsify.domain.use_case.AddArticleToFavouriteUseCase
import javax.inject.Inject

class AddArticleToFavouriteUseCaseImpl @Inject constructor(
    private val repository: FavouriteRepository
) : AddArticleToFavouriteUseCase {

    override suspend fun invoke(article: Article) {
        repository.addToFavourite(article)
    }
}