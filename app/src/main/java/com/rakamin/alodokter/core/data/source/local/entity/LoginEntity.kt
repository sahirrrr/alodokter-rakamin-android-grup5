package com.rakamin.alodokter.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_table")
data class LoginEntity(
    @PrimaryKey
    val id: Int? = null,

    val nama: String? = null,

    val email: String? = null,

    val jenisKelamin: String? = null,

    val umur: Int? = null,

    val tanggalLahir: String? = null,

    val noHp: String? = null,

    val kabupatenKota: String? = null,

    val foto: String? = null,
)