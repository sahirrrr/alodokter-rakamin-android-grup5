package com.rakamin.alodokter.core.data.source.remote.network

import com.rakamin.alodokter.core.data.source.remote.response.ArticleResponse
import com.rakamin.alodokter.core.data.source.remote.response.LoginResponse
import com.rakamin.alodokter.core.data.source.remote.response.RegisterResponse
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Flowable<LoginResponse>

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
}