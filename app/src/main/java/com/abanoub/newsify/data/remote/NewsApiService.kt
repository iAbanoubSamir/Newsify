package com.abanoub.newsify.data.remote

import com.abanoub.newsify.domain.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "06010402445546f1ba2dbeae09d44f38"
    }

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = "us",
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        query: String,
    ): Response<NewsResponse>
}