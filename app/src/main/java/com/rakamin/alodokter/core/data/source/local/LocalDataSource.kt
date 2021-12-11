package com.rakamin.alodokter.core.data.source.local

import com.rakamin.alodokter.core.data.source.local.entity.UserEntity
import com.rakamin.alodokter.core.data.source.local.room.AlodokterDao
import io.reactivex.Flowable

class LocalDataSource(private val dao: AlodokterDao) {

    fun insertUserData(login : List<UserEntity>)  = dao.insertUserData(login)

    fun getUserData() : Flowable<List<UserEntity>> = dao.getUserData()

}