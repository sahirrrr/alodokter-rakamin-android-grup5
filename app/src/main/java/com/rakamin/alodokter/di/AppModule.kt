package com.rakamin.alodokter.di

import com.rakamin.alodokter.domain.usecase.AlodokterInteractor
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase
import com.rakamin.alodokter.ui.login.LoginViewModel
import com.rakamin.alodokter.ui.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AlodokterUseCase> { AlodokterInteractor(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}