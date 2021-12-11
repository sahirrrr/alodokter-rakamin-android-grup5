package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rakamin.alodokter.core.data.source.local.entity.ArticleEntity
import com.rakamin.alodokter.core.data.source.local.entity.LoginEntity

@Database(entities = [LoginEntity::class, ArticleEntity::class], version = 2, exportSchema = false)
abstract class AlodokterDB : RoomDatabase() {
    abstract fun alodokterDao(): AlodokterDao
}