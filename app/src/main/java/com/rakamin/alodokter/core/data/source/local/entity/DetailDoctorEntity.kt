package com.rakamin.alodokter.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor_table")
data class DetailDoctorEntity(

    @PrimaryKey
    val id: Int? = null,

    val nama: String? = null,

    val about: String? = null,

    val hargaKonsul: Int? = null,

    val spesialis: String? = null,

    val lokasi: String? = null,

    val edukasi: String? = null,

    val jurusan: String? = null,

    val fakultas: String? = null,

    val rating: Int? = null,

    val jumlahPasien: Int? = null,

    val jumlahPengalaman: Int? = null,
)
