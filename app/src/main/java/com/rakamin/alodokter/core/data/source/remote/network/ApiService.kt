package com.rakamin.alodokter.core.data.source.remote.network

import com.rakamin.alodokter.core.data.source.remote.response.ForgotPasswordResponse
import com.rakamin.alodokter.core.data.source.remote.response.ArticleResponse
import com.rakamin.alodokter.core.data.source.remote.response.LoginResponse
import com.rakamin.alodokter.core.data.source.remote.response.ProfileResponse
import com.rakamin.alodokter.core.data.source.remote.response.RegisterResponse
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

    @FormUrlEncoded
    @POST("password/forgot")
    fun postForgotPassword(
        @Field("email") email: String
    ) : Flowable<ForgotPasswordResponse>
}

