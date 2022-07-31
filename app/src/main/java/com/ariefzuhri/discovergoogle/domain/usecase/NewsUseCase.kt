package com.ariefzuhri.discovergoogle.domain.usecase

import androidx.paging.PagingData
import com.ariefzuhri.discovergoogle.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

    fun getNews(): Flow<PagingData<Article>>
}