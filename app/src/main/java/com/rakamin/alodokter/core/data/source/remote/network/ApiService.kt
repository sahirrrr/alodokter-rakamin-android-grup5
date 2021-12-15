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
    fun showProfile(
        @Path("id_user") id_user: String
    ): Flowable<ProfileResponse>

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

    @GET("article/detail/{id_article}")
    fun getArticleById(
        @Path("id_article") id_article: Int
    ): Flowable<ArticleResponse>

    @GET("article/search/{judul}")
    fun articleSearch(
        @Path("judul") query : String
    ): Flowable<List<ArticleSearchResponse>>

}

