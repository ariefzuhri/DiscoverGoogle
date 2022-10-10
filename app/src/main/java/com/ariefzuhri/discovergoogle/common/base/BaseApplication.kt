package com.ariefzuhri.discovergoogle.common.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.ariefzuhri.discovergoogle.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        disableNightMode()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                loggerModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }

    private fun disableNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}