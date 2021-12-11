package com.rakamin.alodokter.ui.login

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class LoginViewModel(private val alodokterUseCase: AlodokterUseCase): ViewModel() {

    fun userLogin(email: String, password: String) = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.postLogin(email, password))
}