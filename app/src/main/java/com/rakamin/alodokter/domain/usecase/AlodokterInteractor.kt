package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.data.source.remote.response.ForgotPasswordResponse
import com.rakamin.alodokter.domain.model.LoginModel
import com.rakamin.alodokter.domain.model.RegisterModel
import com.rakamin.alodokter.domain.repository.IAlodokterRepository
import io.reactivex.Flowable

class AlodokterInteractor(private val alodokterRepositoryImp: IAlodokterRepository): AlodokterUseCase {
    override fun postLogin(email: String, password: String): Flowable<Resource<List<LoginModel>>> {
        return alodokterRepositoryImp.postLogin(email, password)
    }

    override fun postRegister(name: String, email: String, password: String, passwordConfirmation: String): Flowable<Resource<List<RegisterModel>>> {
        return alodokterRepositoryImp.postRegister(name, email, password, passwordConfirmation)
    }

    override fun postForgotPassword(email: String) : Flowable<ApiResponse<ForgotPasswordResponse>>{
        return alodokterRepositoryImp.postForgotPassword(email)
    }
}