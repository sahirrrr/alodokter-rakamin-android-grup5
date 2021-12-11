package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rakamin.alodokter.core.data.source.local.entity.LoginEntity
import com.rakamin.alodokter.core.data.source.local.entity.ProfileEntity

@Database(entities = [LoginEntity::class, ProfileEntity::class], version = 2, exportSchema = false)
abstract class AlodokterDB: RoomDatabase() {
    abstract fun alodokterDao() : AlodokterDao
}