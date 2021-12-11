package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rakamin.alodokter.core.data.source.local.entity.LoginEntity
import com.rakamin.alodokter.core.data.source.local.entity.RegisterEntity

@Database(entities = [LoginEntity::class, RegisterEntity::class], version = 1, exportSchema = false)
abstract class AlodokterDB: RoomDatabase() {
    abstract fun alodokterDao() : AlodokterDao
}