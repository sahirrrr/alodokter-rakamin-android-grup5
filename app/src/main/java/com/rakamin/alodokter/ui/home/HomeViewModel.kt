package com.rakamin.alodokter.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class HomeViewModel(private val alodokterUseCase: AlodokterUseCase) : ViewModel() {

    fun getArticle() = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getArticle())
}