package com.rakamin.alodokter.domain.usecase

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.domain.model.LoginModel
import com.rakamin.alodokter.domain.model.ProfileModel
import io.reactivex.Flowable

interface AlodokterUseCase {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<LoginModel>>>
    fun getProfile(idUser:String) : Flowable<Resource<List<ProfileModel>>>
}