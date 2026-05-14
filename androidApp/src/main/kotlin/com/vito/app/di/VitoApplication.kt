package com.vito.app.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * VITO Application - Koin DI bootstrap
 */
class VitoApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@VitoApplication)
            modules(appModule)
        }
    }
}