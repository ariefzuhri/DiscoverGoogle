package com.ariefzuhri.discovergoogle.data.source.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ariefzuhri.discovergoogle.common.util.orZero
import com.ariefzuhri.discovergoogle.data.source.remote.network.ApiService
import com.ariefzuhri.discovergoogle.data.source.remote.response.NewsResponse
import com.ariefzuhri.discovergoogle.data.source.remote.response.util.RemoteResponseStatus
import java.io.IOException
import kotlin.math.ceil

class NewsPagingSource(private val apiService: ApiService) :
    PagingSource<Int, NewsResponse.ArticlesItem>() {

    companion object {
        private val TAG = NewsPagingSource::class.java.simpleName.toString()
        private const val START_PAGE_NUMBER = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsResponse.ArticlesItem> {
        val nextPageNumber = params.key ?: START_PAGE_NUMBER
        return try {
            val response = apiService.getNews(page = nextPageNumber, pageSize = params.loadSize)
            if (response.status == RemoteResponseStatus.SUCCESS.id &&
                response.articles?.isNotEmpty() == true
            ) {
                val totalPageNumber = ceil(
                    response.totalResults.orZero() / params.loadSize.toFloat()
                )
                @Suppress("UNCHECKED_CAST")
                LoadResult.Page(
                    data = response.articles as List<NewsResponse.ArticlesItem>,
                    prevKey = null,
                    nextKey = if (nextPageNumber < totalPageNumber) nextPageNumber + 1 else null
                )
            } else {
                val e = Exception(response.message)
                LoadResult.Error(e)
            }
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> "Please check your internet connection"
                else -> "Something went wrong: ${e.message}"
            }
            Log.e(TAG, errorMessage, e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsResponse.ArticlesItem>): Int? {
        return null
    }
}