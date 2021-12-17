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

    @FormUrlEncoded
    @POST("pasien/register")
    fun postRegister(
        @Field("nama") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String,
    ): Flowable<RegisterResponse>

    @GET("pasien/detail/{id_user}")
    fun showProfile(@Path("id_user") id_user : String): Flowable<ProfileResponse>

    @FormUrlEncoded
    @PUT("pasien/update/{id_user}")
    fun putUserProfile(
        @Path("id_user") id_user: String,
        @Field("no_hp") no_hp: String,
        @Field("tanggal_lahir") tanggal_lahir: String,
        @Field("kabupaten_kota") kabupaten_kota: String,
    ) : Flowable<EditProfileResponse>

    @GET("article")
    fun getArticles(): Flowable<ArticleResponse>

    @FormUrlEncoded
    @POST("password/forgot")
    fun postForgotPassword(
        @Field("email") email: String
    ) : Flowable<ForgotPasswordResponse>

    @GET ("doctor")
    fun getDoctor(): Flowable<ListDoctorResponse>

    @GET("doctor/detail/{id_doctor}")
    fun getDoctorDetail(@Path("id_doctor") id_doctor : String) : Flowable<DetailDoctorResponse>

    @GET("doctor/search/{nama}")
    fun searchDoctor(
        @Path("nama") query : String
    ): Flowable<List<DoctorResponse>>
}

