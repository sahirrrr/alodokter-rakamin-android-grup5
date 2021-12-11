package com.rakamin.alodokter.core.data.source.remote.network

import com.rakamin.alodokter.core.data.source.remote.response.LoginResponse
import com.rakamin.alodokter.core.data.source.remote.response.ProfileResponse
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
}


