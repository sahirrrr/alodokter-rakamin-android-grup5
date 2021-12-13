package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.ForgotPasswordResponse
import com.rakamin.alodokter.domain.model.LoginModel
import com.rakamin.alodokter.domain.model.RegisterModel
import io.reactivex.Flowable

interface AlodokterUseCase {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<LoginModel>>>

    fun postRegister(name: String, email: String, password: String, passwordConfirmation: String) : Flowable<Resource<List<RegisterModel>>>

    fun postForgotPassword(email: String) : Flowable<ApiResponse<ForgotPasswordResponse>>
}