package com.rakamin.alodokter.domain.repository

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.ForgotPasswordResponse
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.model.DetailDoctorModel
import com.rakamin.alodokter.domain.model.RegisterModel
import com.rakamin.alodokter.domain.model.UserModel
import io.reactivex.Flowable

interface IAlodokterRepository {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<UserModel>>>

    fun postRegister(name: String, email: String, password: String, passwordConfirmation: String) : Flowable<Resource<List<RegisterModel>>>

    fun postForgotPassword(email: String) : Flowable<ApiResponse<ForgotPasswordResponse>>

    fun getUserData() : Flowable<List<UserModel>>

    fun getProfile(idUser: String) : Flowable<Resource<List<UserModel>>>

    fun getDoctorDetail(idDoctor: String) : Flowable<Resource<List<DetailDoctorModel>>>

    fun getArticle() : Flowable<Resource<List<ArticleModel>>>

    fun userLogout()
    
}