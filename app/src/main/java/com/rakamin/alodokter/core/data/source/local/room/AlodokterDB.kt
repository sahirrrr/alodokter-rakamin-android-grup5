package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false)
abstract class AlodokterDB: RoomDatabase() {
    abstract fun alodokterDao() : AlodokterDao
}