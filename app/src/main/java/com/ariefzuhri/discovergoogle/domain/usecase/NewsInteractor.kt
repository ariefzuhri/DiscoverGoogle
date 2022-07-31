package com.ariefzuhri.discovergoogle.domain.usecase

import androidx.paging.PagingData
import com.ariefzuhri.discovergoogle.domain.model.Article
import com.ariefzuhri.discovergoogle.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val repository: INewsRepository) : NewsUseCase {

    override fun getNews(): Flow<PagingData<Article>> {
        return repository.getNews()
    }
}