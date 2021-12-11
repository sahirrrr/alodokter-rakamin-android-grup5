package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.model.LoginModel
import io.reactivex.Flowable
import java.util.concurrent.Flow

interface AlodokterUseCase {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<LoginModel>>>
    fun getArticle() : Flowable<Resource<List<ArticleModel>>>
}