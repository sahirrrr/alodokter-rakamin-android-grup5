package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.DoctorResponse
import com.rakamin.alodokter.core.data.source.remote.response.ForgotPasswordResponse
import com.rakamin.alodokter.core.data.source.remote.response.User
import com.rakamin.alodokter.domain.model.*
import com.rakamin.alodokter.core.data.source.remote.response.ArticleSearchResponse
import io.reactivex.Flowable

interface AlodokterUseCase {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<UserModel>>>

    fun getUserData() : Flowable<List<UserModel>>

    fun putUserProfile(idUser: String, noHp: String, tglLahir: String, kotaKab: String) : Flowable<Resource<List<UserModel>>>

    fun postRegister(name: String, email: String, password: String, passwordConfirmation: String) : Flowable<Resource<List<RegisterModel>>>

    fun postForgotPassword(email: String) : Flowable<ApiResponse<ForgotPasswordResponse>>

    fun getProfile(idUser: String) : Flowable<Resource<List<UserModel>>>

    fun getArticle() : Flowable<Resource<List<ArticleModel>>>

    fun getArticleById(id : Int) : Flowable<Resource<List<ArticleModel>>>

    fun articleSearch(query: String): Flowable<ApiResponse<List<ArticleSearchResponse>>>

    fun getDoctor() : Flowable<Resource<List<ListDoctorModel>>>

    fun getDoctorDetail(idDoctor: String) : Flowable<Resource<List<DetailDoctorModel>>>

    fun searchDoctor(query: String): Flowable<ApiResponse<List<DoctorResponse>>>

    fun userLogout()

}