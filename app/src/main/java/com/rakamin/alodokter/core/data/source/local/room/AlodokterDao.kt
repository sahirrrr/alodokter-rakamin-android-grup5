package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rakamin.alodokter.core.data.source.local.entity.UserEntity
import com.rakamin.alodokter.core.data.source.local.entity.ArticleEntity
import com.rakamin.alodokter.core.data.source.local.entity.RegisterEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface AlodokterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(login: List<UserEntity>) : Completable

    @Query("select * from user_table")
    fun getUserData() : Flowable<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: List<ArticleEntity>) : Completable

    @Query("SELECT * FROM article_table")
    fun getArticle() : Flowable<List<ArticleEntity>>
  
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserRegister(login: List<RegisterEntity>) : Completable

    @Query("select * from register_table")
    fun getUserRegister() : Flowable<List<RegisterEntity>>
}