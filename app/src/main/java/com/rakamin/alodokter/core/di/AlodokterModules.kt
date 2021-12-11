package com.rakamin.alodokter.core.di

import androidx.room.Room
import com.rakamin.alodokter.core.data.AlodokterRepository
import com.rakamin.alodokter.core.data.source.local.LocalDataSource
import com.rakamin.alodokter.core.data.source.local.room.AlodokterDB
import com.rakamin.alodokter.core.data.source.remote.RemoteDataSource
import com.rakamin.alodokter.core.data.source.remote.network.ApiService
import com.rakamin.alodokter.domain.repository.IAlodokterRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<AlodokterDB>().alodokterDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AlodokterDB::class.java, "alodokter_db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://janjidokter.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val alodokterModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IAlodokterRepository> { AlodokterRepository(get(), get()) }
}