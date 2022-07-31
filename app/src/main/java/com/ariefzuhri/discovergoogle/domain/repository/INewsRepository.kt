package com.ariefzuhri.discovergoogle.domain.repository

import androidx.paging.PagingData
import com.ariefzuhri.discovergoogle.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getNews(): Flow<PagingData<Article>>
}