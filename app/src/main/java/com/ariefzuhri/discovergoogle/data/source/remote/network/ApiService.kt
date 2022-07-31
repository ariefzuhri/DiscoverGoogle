package com.ariefzuhri.discovergoogle.data.source.remote.network

import com.ariefzuhri.discovergoogle.BuildConfig
import com.ariefzuhri.discovergoogle.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything?domains=googleblog.com&language=en&sortBy=publishedAt&apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun getNews(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
    ): NewsResponse
}