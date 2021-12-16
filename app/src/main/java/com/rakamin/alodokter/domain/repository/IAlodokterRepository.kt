package com.rakamin.alodokter.domain.repository

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.DoctorSearchResponse
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.model.DoctorModel
import com.rakamin.alodokter.domain.model.RegisterModel
import com.rakamin.alodokter.domain.model.UserModel
import io.reactivex.Flowable

interface IAlodokterRepository {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<UserModel>>>

    fun getUserData() : Flowable<List<UserModel>>

    fun getProfile(idUser: String) : Flowable<Resource<List<UserModel>>>

    fun getArticle() : Flowable<Resource<List<ArticleModel>>>

    fun getDoctor() : Flowable<Resource<List<DoctorModel>>>
  
    fun postRegister(name: String, email: String, password: String, passwordConfirmation: String) : Flowable<Resource<List<RegisterModel>>>

    fun userLogout()

    fun getDoctorById(id: Int): Flowable<Resource<List<DoctorModel>>>

    fun searchDoctor(query: String): Flowable<ApiResponse<List<DoctorSearchResponse>>>
}