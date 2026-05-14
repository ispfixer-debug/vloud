package com.vito.app.android

import android.app.Application
import com.vito.app.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * VITO Application class
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}