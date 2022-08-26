package com.ariefzuhri.discovergoogle.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ariefzuhri.discovergoogle.data.mapper.toDomain
import com.ariefzuhri.discovergoogle.data.source.remote.NewsPagingSource
import com.ariefzuhri.discovergoogle.domain.model.Article
import com.ariefzuhri.discovergoogle.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(private val pagingSource: NewsPagingSource) : INewsRepository {

    override fun getNews(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
    }
}