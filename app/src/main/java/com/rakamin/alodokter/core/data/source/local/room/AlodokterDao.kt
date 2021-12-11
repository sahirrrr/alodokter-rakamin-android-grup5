package com.rakamin.alodokter.core.data.source.local.room

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rakamin.alodokter.core.data.source.local.entity.LoginEntity
import com.rakamin.alodokter.core.data.source.local.entity.ProfileEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface AlodokterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserLogin(login: List<LoginEntity>) : Completable

    @Query("select * from login_table")
    fun getUserLogin() : Flowable<List<LoginEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserProfile(profileEntity: List<ProfileEntity> ) : Completable

    @Query("select * from table_profile")
    fun getUserProfile() : Flowable<List<ProfileEntity>>
}