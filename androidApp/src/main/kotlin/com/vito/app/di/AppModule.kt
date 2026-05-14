package com.vito.app.di

import com.vito.app.data.supabase.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * App Module - Core Koin DI bindings
 */
val appModule = module {
    // Repositories
    single<AuthRepository> { AuthRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl() }
}