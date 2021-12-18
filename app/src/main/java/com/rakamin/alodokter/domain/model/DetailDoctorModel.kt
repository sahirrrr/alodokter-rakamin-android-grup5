package com.rakamin.alodokter.domain.model

import com.rakamin.alodokter.core.data.source.remote.response.ScheduleItem

data class DetailDoctorModel(
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
