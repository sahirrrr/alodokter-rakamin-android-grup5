package com.rakamin.alodokter.ui.article

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class ArticleViewModel(private val alodokterUseCase: AlodokterUseCase) : ViewModel() {

    fun getArticle() = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getArticle())
    fun getArticleById(id: Int) = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getArticleById(id))
}