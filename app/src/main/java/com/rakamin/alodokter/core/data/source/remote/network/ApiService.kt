package com.rakamin.alodokter.core.data.source.remote.network

import com.rakamin.alodokter.core.data.source.remote.response.*
import io.reactivex.Flowable
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Flowable<LoginResponse>

    @GET("pasien/detail/{id_user}")
    fun showProfile(@Path("id_user") id_user : String): Flowable<ProfileResponse>

    @GET("article")
    fun getArticles(): Flowable<ArticleResponse>
  
    @FormUrlEncoded
    @POST("pasien/register")
    fun postRegister(
        @Field("nama") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String,
    ): Flowable<RegisterResponse>

    @GET ("doctor")
    fun getDoctor(): Flowable<DoctorResponse>

    @GET("doctor/detail/{id_doctor}")
    fun getDocterById(
        @Path("id_doctor")id_doctor: Int
    ): Flowable<DoctorResponse>

    @GET("doctor/search/{nama}")
    fun searchDoctor(
        @Path("nama") query : String
        ): Flowable<List<DoctorSearchResponse>>

}

