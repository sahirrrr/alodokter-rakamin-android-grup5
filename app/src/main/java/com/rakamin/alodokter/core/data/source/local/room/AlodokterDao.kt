package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rakamin.alodokter.core.data.source.local.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface AlodokterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(login: List<UserEntity>) : Completable

    @Query("select * from user_table")
    fun getUserData() : Flowable<List<UserEntity>>

}