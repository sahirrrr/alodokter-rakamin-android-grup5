package com.rakamin.alodokter.core.data.source.remote.network

import com.rakamin.alodokter.core.data.source.remote.response.LoginResponse
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Flowable<LoginResponse>
}