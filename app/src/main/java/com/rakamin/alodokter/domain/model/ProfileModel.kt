package com.rakamin.alodokter.domain.model

import androidx.room.PrimaryKey

data class ProfileModel(
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
