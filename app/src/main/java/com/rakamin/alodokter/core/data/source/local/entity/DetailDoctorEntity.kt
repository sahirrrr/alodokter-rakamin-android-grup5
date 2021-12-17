package com.rakamin.alodokter.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rakamin.alodokter.core.data.source.remote.response.ScheduleItem

@Entity(tableName = "detail_doctor_table")
data class DetailDoctorEntity(
    @PrimaryKey
    val id: Int? = null,

    val nama: String? = null,

    val about: String? = null,

    val spesialis: String? = null,

    val hargaKonsul: Int? = null,

    val jumlahPasien: Int? = null,

    val jumlahPengalaman: Int? = null,

    val rating: Int? = null,

    val lokasi: String? = null,

    val rumahSakit: String? = null,

    val alamat: String? = null,

    val schedule: List<ScheduleItem>? = null,

    val edukasi: String? = null,

    val fakultas: String? = null,

    val jurusan: String? = null,

    val foto: String? = null
)

