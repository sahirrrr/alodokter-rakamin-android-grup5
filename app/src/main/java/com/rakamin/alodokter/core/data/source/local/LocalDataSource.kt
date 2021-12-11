package com.rakamin.alodokter.core.data.source.local

import com.rakamin.alodokter.core.data.source.local.entity.LoginEntity
import com.rakamin.alodokter.core.data.source.local.room.AlodokterDao
import io.reactivex.Flowable

class LocalDataSource(private val dao: AlodokterDao) {

    fun insertUserLogin(login : List<LoginEntity>)  = dao.insertUserLogin(login)

    fun getUserLogin() : Flowable<List<LoginEntity>> = dao.getUserLogin()
}