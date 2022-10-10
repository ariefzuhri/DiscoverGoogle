package com.ariefzuhri.discovergoogle.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ariefzuhri.discovergoogle.common.util.orZero
import com.ariefzuhri.discovergoogle.data.source.remote.logger.ErrorLogger
import com.ariefzuhri.discovergoogle.data.source.remote.network.ApiService
import com.ariefzuhri.discovergoogle.data.source.remote.response.NewsResponse
import com.ariefzuhri.discovergoogle.data.source.remote.response.ResponseStatus
import kotlin.math.ceil

class NewsPagingSource(
    private val apiService: ApiService,
    private val remoteErrorLogger: ErrorLogger,
) :
    PagingSource<Int, NewsResponse.ArticlesItem>() {

    companion object {
        private val TAG = NewsPagingSource::class.java.simpleName
        private const val START_PAGE_NUMBER = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsResponse.ArticlesItem> {
        val nextPageNumber = params.key ?: START_PAGE_NUMBER
        return try {
            val response = apiService.getNews(page = nextPageNumber, pageSize = params.loadSize)
            if (response.status == ResponseStatus.SUCCESS.id && response.articles != null) {
                val totalPageNumber = ceil(
                    response.totalResults.orZero() / params.loadSize.toFloat()
                ).toInt()
                @Suppress("UNCHECKED_CAST")
                LoadResult.Page(
                    data = response.articles as List<NewsResponse.ArticlesItem>,
                    prevKey = null,
                    nextKey = if (nextPageNumber == totalPageNumber) null else nextPageNumber + 1
                )
            } else {
                LoadResult.Error(remoteErrorLogger.e(TAG, response.message))
            }
        } catch (e: Exception) {
            LoadResult.Error(remoteErrorLogger.e(TAG, e))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsResponse.ArticlesItem>): Int? {
        return null
    }
}