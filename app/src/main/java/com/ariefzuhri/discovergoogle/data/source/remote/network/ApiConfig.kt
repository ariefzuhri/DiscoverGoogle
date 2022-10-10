package com.ariefzuhri.discovergoogle.data.source.remote.network

import android.content.Context
import com.ariefzuhri.discovergoogle.BuildConfig
import com.ariefzuhri.discovergoogle.common.util.getHostName
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    private const val NETWORK_TIMEOUT_SECONDS = 120L

    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .build()
    }

    fun provideCertificatePinner(): CertificatePinner {
        val hostName = BuildConfig.NEWS_BASE_URL.getHostName()
        return CertificatePinner.Builder()
            .add(hostName, BuildConfig.NEWS_PUBLIC_KEY_1)
            .add(hostName, BuildConfig.NEWS_PUBLIC_KEY_2)
            .add(hostName, BuildConfig.NEWS_PUBLIC_KEY_3)
            .add(hostName, BuildConfig.NEWS_PUBLIC_KEY_4)
            .build()
    }

    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        certificatePinner: CertificatePinner,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .certificatePinner(certificatePinner)
            .connectTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .callTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}