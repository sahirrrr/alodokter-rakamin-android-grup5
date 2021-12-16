package com.rakamin.alodokter.ui.login

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class ForgotPasswordViewModel(private val alodokterUseCase: AlodokterUseCase) : ViewModel() {

    fun userForgotPassword(email: String) =
        LiveDataReactiveStreams.fromPublisher(alodokterUseCase.postForgotPassword(email))
}