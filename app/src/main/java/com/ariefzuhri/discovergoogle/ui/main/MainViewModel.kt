package com.ariefzuhri.discovergoogle.ui.main

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ariefzuhri.discovergoogle.domain.model.Article
import com.ariefzuhri.discovergoogle.domain.usecase.NewsUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(useCase: NewsUseCase) : ViewModel() {

    val news: Flow<PagingData<Article>> =
        useCase.getNews()
}