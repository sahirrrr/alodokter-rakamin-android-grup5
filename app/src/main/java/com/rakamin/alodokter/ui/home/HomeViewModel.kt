package com.rakamin.alodokter.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class HomeViewModel(private val alodokterUseCase: AlodokterUseCase) : ViewModel() {
    fun getArticle(): LiveData<Resource<List<ArticleModel>>> {
       return LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getArticle())
    }
}