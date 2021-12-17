package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rakamin.alodokter.core.data.source.local.entity.*

@Database(entities = [UserEntity::class, RegisterEntity::class, ArticleEntity::class, ListDoctorEntity::class, DetailDoctorEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class AlodokterDB : RoomDatabase() {
    abstract fun alodokterDao(): AlodokterDao
}