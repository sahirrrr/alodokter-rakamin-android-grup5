package com.rakamin.alodokter.domain.repository

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.core.data.source.remote.response.RegisterResponse
import com.rakamin.alodokter.domain.model.LoginModel
import com.rakamin.alodokter.domain.model.RegisterModel
import io.reactivex.Flowable

interface IAlodokterRepository {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<LoginModel>>>

    fun getArticle() : Flowable<Resource<List<ArticleModel>>>
  
    fun postRegister(name: String, email: String, password: String, passwordConfirmation: String) : Flowable<Resource<List<RegisterModel>>>
}