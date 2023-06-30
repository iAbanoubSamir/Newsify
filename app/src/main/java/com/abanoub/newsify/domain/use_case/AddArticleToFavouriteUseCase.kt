package com.abanoub.newsify.domain.use_case

import com.abanoub.newsify.domain.model.Article

interface AddArticleToFavouriteUseCase {

    suspend operator fun invoke(article: Article)
}