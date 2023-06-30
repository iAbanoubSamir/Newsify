package com.abanoub.newsify.data.use_case

import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.repository.FavouriteRepository
import com.abanoub.newsify.domain.use_case.DeleteArticleFromFavouriteUseCase
import javax.inject.Inject

class DeleteArticleFromFavouriteUseCaseImpl @Inject constructor(
    private val repository: FavouriteRepository
) : DeleteArticleFromFavouriteUseCase {

    override suspend fun invoke(article: Article) {
        repository.deleteFromFavourite(article)
    }
}