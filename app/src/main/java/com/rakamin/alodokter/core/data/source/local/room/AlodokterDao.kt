package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rakamin.alodokter.core.data.source.local.entity.ArticleEntity
import com.rakamin.alodokter.core.data.source.local.entity.LoginEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface AlodokterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserLogin(login: List<LoginEntity>) : Completable

    @Query("select * from login_table")
    fun getUserLogin() : Flowable<List<LoginEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: List<ArticleEntity>) : Completable

    @Query("SELECT * FROM article_table")
    fun getArticle() : Flowable<List<ArticleEntity>>
}