package com.abanoub.newsify.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abanoub.newsify.domain.model.Article
import com.abanoub.newsify.domain.use_case.GetNewsUseCase
import com.abanoub.newsify.domain.use_case.SearchNewsUseCase
import com.abanoub.newsify.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val searchNewsUseCase: SearchNewsUseCase
) : ViewModel() {

    private var _news = MutableStateFlow<Resource<List<Article>>>(Resource.Empty)
    val news = _news.asStateFlow()

    private var _search = MutableStateFlow<Resource<List<Article>>>(Resource.Empty)
    val search = _search.asStateFlow()

    init {
        getNews()
    }


    fun getNews(countryCode: String = "us") {
        viewModelScope.launch {
            getNewsUseCase(countryCode).collect { resource ->
                _news.emit(resource)
            }
        }
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            searchNewsUseCase(query).collect { resource ->
                _search.emit(resource)
            }
        }
    }
}