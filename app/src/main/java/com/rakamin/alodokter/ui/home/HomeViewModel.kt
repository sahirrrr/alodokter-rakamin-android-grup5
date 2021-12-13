package com.rakamin.alodokter.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class HomeViewModel(private val alodokterUseCase: AlodokterUseCase) : ViewModel() {

    fun getArticle() = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getArticle())

    fun userProfile(idUser : String) = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getProfile(idUser))

    val getUserData = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getUserData())
}