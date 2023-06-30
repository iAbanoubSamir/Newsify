package com.abanoub.newsify.presentation.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.use_case.AddArticleToFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class ArticleViewModel(
    private val addArticleToFavouriteUseCase: AddArticleToFavouriteUseCase
) : ViewModel() {

    fun addToFavourite(article: Article) = viewModelScope.launch {
        addArticleToFavouriteUseCase(article)
    }
}