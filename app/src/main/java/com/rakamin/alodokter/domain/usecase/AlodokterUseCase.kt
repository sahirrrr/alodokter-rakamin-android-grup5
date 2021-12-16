package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.domain.model.UserModel
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.model.DetailDoctorModel
import com.rakamin.alodokter.domain.model.RegisterModel
import io.reactivex.Flowable

interface AlodokterUseCase {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<UserModel>>>

    fun postRegister(name: String, email: String, password: String, passwordConfirmation: String) : Flowable<Resource<List<RegisterModel>>>

    fun getUserData() : Flowable<List<UserModel>>

    fun getProfile(idUser: String) : Flowable<Resource<List<UserModel>>>

    fun getDoctorDetail(idDoctor: String) : Flowable<Resource<List<DetailDoctorModel>>>

    fun getArticle() : Flowable<Resource<List<ArticleModel>>>

    fun userLogout()
}