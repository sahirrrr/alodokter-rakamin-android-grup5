package com.rakamin.alodokter.domain.model

data class DetailDoctorModel(
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
