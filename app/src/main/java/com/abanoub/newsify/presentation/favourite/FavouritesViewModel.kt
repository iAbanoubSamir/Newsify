package com.abanoub.newsify.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.use_case.DeleteArticleFromFavouriteUseCase
import com.abanoub.newsify.domain.use_case.GetFavouriteNewsUseCase
import com.abanoub.newsify.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouriteNewsUseCase: GetFavouriteNewsUseCase,
    private val deleteArticleFromFavouriteUseCase: DeleteArticleFromFavouriteUseCase
) : ViewModel() {

    private var _favourites = MutableStateFlow<Resource<List<Article>>>(Resource.Empty)
    val favourites = _favourites.asStateFlow()

    init {
        getFavourites()
    }

    fun deleteFavourite(article: Article) {
        viewModelScope.launch {
            deleteArticleFromFavouriteUseCase(article)
        }
    }

    fun getFavourites() {
        viewModelScope.launch {
            getFavouriteNewsUseCase().collect { resource ->
                _favourites.emit(resource)
            }
        }
    }
}