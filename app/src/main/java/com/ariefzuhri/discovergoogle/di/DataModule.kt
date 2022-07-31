package com.ariefzuhri.discovergoogle.di

import com.ariefzuhri.discovergoogle.data.repository.NewsRepository
import com.ariefzuhri.discovergoogle.data.source.remote.NewsPagingSource
import com.ariefzuhri.discovergoogle.data.source.remote.network.ApiConfig
import com.ariefzuhri.discovergoogle.domain.repository.INewsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single {
        ApiConfig.provideChuckerInterceptor(context = androidContext())
    }
    single {
        ApiConfig.provideCertificatePinner()
    }
    single {
        ApiConfig.provideOkHttpClient(chuckerInterceptor = get(), certificatePinner = get())
    }
    single {
        ApiConfig.provideRetrofit(okHttpClient = get())
    }
    single {
        ApiConfig.provideApiService(retrofit = get())
    }
}

val repositoryModule = module {
    single {
        NewsPagingSource(apiService = get())
    }
    single<INewsRepository> {
        NewsRepository(pagingSource = get())
    }
}