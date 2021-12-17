package com.rakamin.alodokter.core.data.source.local

import com.rakamin.alodokter.core.data.source.local.entity.*
import com.rakamin.alodokter.core.data.source.local.room.AlodokterDao
import io.reactivex.Flowable

class LocalDataSource(private val dao: AlodokterDao) {

    fun insertUserData(login : List<UserEntity>)  = dao.insertUserData(login)

    fun getUserData() : Flowable<List<UserEntity>> = dao.getUserData()

    fun insertUserRegister(register : List<RegisterEntity>)  = dao.insertUserRegister(register)

    fun getUserRegister() : Flowable<List<RegisterEntity>> = dao.getUserRegister()

    fun insertArticle(article : List<ArticleEntity>) = dao.insertArticle(article)

    fun getArticles() : Flowable<List<ArticleEntity>> = dao.getArticle()

    fun insertDoctor(listDoctor: List<ListDoctorEntity>) = dao.insertDoctor(listDoctor)

    fun getDoctor() : Flowable<List<ListDoctorEntity>> = dao.getDoctor()

    fun insertDoctorDetail(detailDoctor: List<DetailDoctorEntity>) = dao.insertDoctorDetail(detailDoctor)

    fun getDoctorDetail(idDoctor: String) = dao.getDoctorDetail(idDoctor)

    fun userLogout() = dao.userLogout()

    fun getArticleById(id: Int) : Flowable<List<ArticleEntity>> = dao.getArticleById(id)

}