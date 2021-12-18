package com.rakamin.alodokter.domain.repository

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.DoctorResponse
import com.rakamin.alodokter.core.data.source.remote.response.ForgotPasswordResponse
import com.rakamin.alodokter.domain.model.*
import com.rakamin.alodokter.core.data.source.remote.response.ArticleSearchResponse
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.model.RegisterModel
import com.rakamin.alodokter.domain.model.UserModel
import io.reactivex.Flowable

interface IAlodokterRepository {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<UserModel>>>

    fun putUserProfile(accessToken: String, idUser: String, name: String, noHp: String, tglLahir: String, kotaKab: String) : Flowable<Resource<List<UserModel>>>

    fun getUserData() : Flowable<List<UserModel>>

    fun postRegister(name: String, email: String, password: String, passwordConfirmation: String) : Flowable<Resource<List<RegisterModel>>>

    fun postForgotPassword(email: String) : Flowable<ApiResponse<ForgotPasswordResponse>>

    fun getProfile(idUser: String) : Flowable<Resource<List<UserModel>>>

    fun getArticle() : Flowable<Resource<List<ArticleModel>>>

    fun getDoctor() : Flowable<Resource<List<ListDoctorModel>>>

    fun getArticleById(id: Int) : Flowable<Resource<List<ArticleModel>>>

    fun articleSearch(query: String): Flowable<ApiResponse<List<ArticleSearchResponse>>>

    fun getDoctorDetail(idDoctor: String) : Flowable<Resource<List<DetailDoctorModel>>>

    fun searchDoctor(query: String): Flowable<ApiResponse<List<DoctorResponse>>>

    fun userLogout()

}