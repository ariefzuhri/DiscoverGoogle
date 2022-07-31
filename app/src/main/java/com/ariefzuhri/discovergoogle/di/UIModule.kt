package com.ariefzuhri.discovergoogle.di

import com.ariefzuhri.discovergoogle.domain.usecase.NewsInteractor
import com.ariefzuhri.discovergoogle.domain.usecase.NewsUseCase
import com.ariefzuhri.discovergoogle.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> {
        NewsInteractor(repository = get())
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(useCase = get())
    }
}