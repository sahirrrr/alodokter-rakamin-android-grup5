package com.rakamin.alodokter.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_profile")
data class ProfileEntity (

    @PrimaryKey
    val id: Int? = null,

    val nama: String? = null,

    val email: String? = null,

    val tanggalLahir: String? = null,

    val umur: Int? = null,

    val jenisKelamin: String? = null,

    val noHp: String? = null,

    val foto: String? = null,

    val kabupatenKota: String? = null,
)