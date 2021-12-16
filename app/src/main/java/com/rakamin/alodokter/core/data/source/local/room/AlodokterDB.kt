package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rakamin.alodokter.core.data.source.local.entity.UserEntity
import com.rakamin.alodokter.core.data.source.local.entity.ArticleEntity
import com.rakamin.alodokter.core.data.source.local.entity.DetailDoctorEntity
import com.rakamin.alodokter.core.data.source.local.entity.RegisterEntity

@Database(entities = [UserEntity::class, RegisterEntity::class, DetailDoctorEntity::class, ArticleEntity::class], version = 1, exportSchema = false)
abstract class AlodokterDB : RoomDatabase() {
    abstract fun alodokterDao(): AlodokterDao
}