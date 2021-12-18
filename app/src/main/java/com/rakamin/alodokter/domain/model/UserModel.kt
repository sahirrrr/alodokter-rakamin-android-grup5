package com.rakamin.alodokter.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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

    val token: String? = null
) : Parcelable
