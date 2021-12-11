package com.rakamin.alodokter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("user")
	val user: UserLogin? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class UserLogin(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String? = null,

	@field:SerializedName("umur")
	val umur: Int? = null,

	@field:SerializedName("tanggal_lahir")
	val tanggalLahir: String? = null,

	@field:SerializedName("no_hp")
	val noHp: String? = null,

	@field:SerializedName("kabupaten_kota")
	val kabupatenKota: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

)
