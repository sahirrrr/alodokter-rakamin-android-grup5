package com.rakamin.alodokter

import android.app.Application
import com.rakamin.alodokter.core.di.alodokterModule
import com.rakamin.alodokter.core.di.databaseModule
import com.rakamin.alodokter.core.di.networkModule
import com.rakamin.alodokter.di.useCaseModule
import com.rakamin.alodokter.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    alodokterModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}