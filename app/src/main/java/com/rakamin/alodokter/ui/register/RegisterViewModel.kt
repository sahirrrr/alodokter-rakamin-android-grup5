package com.rakamin.alodokter.ui.register

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class RegisterViewModel(private val alodokterUseCase: AlodokterUseCase): ViewModel() {
    fun userRegister(name: String, email: String, password: String, passwordConfirmation: String) =
        LiveDataReactiveStreams.fromPublisher(alodokterUseCase.postRegister(name, email, password, passwordConfirmation))
}