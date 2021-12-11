package com.rakamin.alodokter.domain.repository

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.response.User
import com.rakamin.alodokter.domain.model.LoginModel
import com.rakamin.alodokter.domain.model.ProfileModel
import io.reactivex.Flowable

interface IAlodokterRepository {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<LoginModel>>>
    fun getProfile(idUser: String) : Flowable<Resource<List<ProfileModel>>>
}