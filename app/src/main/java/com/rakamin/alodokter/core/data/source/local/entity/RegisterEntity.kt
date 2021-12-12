package com.rakamin.alodokter.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register_table")
data class RegisterEntity(
    @PrimaryKey
    val id: Int,

    val email: String,

    val token: String
)
