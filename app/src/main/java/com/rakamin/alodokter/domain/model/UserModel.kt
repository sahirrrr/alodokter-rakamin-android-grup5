package com.rakamin.alodokter.domain.model

data class UserModel(
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
