package com.rakamin.alodokter.domain.repository

import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.domain.model.UserModel
import io.reactivex.Flowable

interface IAlodokterRepository {

    fun postLogin(email:String, password:String) : Flowable<Resource<List<UserModel>>>

    fun getProfile(idUser: String) : Flowable<Resource<List<UserModel>>>
}