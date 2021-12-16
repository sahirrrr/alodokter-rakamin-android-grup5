package com.rakamin.alodokter.core.data.source.local

import com.rakamin.alodokter.core.data.source.local.entity.ArticleEntity
import com.rakamin.alodokter.core.data.source.local.entity.DoctorEntity
import com.rakamin.alodokter.core.data.source.local.entity.RegisterEntity
import com.rakamin.alodokter.core.data.source.local.entity.UserEntity
import com.rakamin.alodokter.core.data.source.local.room.AlodokterDao
import io.reactivex.Flowable
import java.net.IDN

class LocalDataSource(private val dao: AlodokterDao) {

    fun insertUserData(login : List<UserEntity>)  = dao.insertUserData(login)

    fun getUserData() : Flowable<List<UserEntity>> = dao.getUserData()

    fun insertUserRegister(register : List<RegisterEntity>)  = dao.insertUserRegister(register)

    fun getUserRegister() : Flowable<List<RegisterEntity>> = dao.getUserRegister()

    fun insertArticle(article : List<ArticleEntity>) = dao.insertArticle(article)

    fun getArticles() : Flowable<List<ArticleEntity>> = dao.getArticle()

    fun userLogout() = dao.userLogout()

    fun insertDoctor(doctor: List<DoctorEntity>) = dao.insertDoctor(doctor)

    fun getDoctor() : Flowable<List<DoctorEntity>> = dao.getDoctor()

    fun getDoctorById(id: Int) : Flowable<List<DoctorEntity>> = dao.getDoctorById(id)

    fun searchDoctor(query: String) : Flowable<List<DoctorEntity>> = dao.searchDoctor(query)
}