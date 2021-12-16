package com.rakamin.alodokter.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_doctor_table")
data class ListDoctorEntity (
    @PrimaryKey
    val id: Int? = null,

    val nama: String? = null,

    val spesialis: String? = null,

    val rumahSakit: String? = null,

    val hargaKonsul: Int? = null
)