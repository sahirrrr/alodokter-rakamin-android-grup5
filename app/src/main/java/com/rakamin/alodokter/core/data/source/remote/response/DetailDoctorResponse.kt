package com.rakamin.alodokter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailDoctorResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("about")
	val about: String? = null,

	@field:SerializedName("harga_konsul")
	val hargaKonsul: Int? = null,

	@field:SerializedName("spesialis")
	val spesialis: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("edukasi")
	val edukasi: String? = null,

	@field:SerializedName("jurusan")
	val jurusan: String? = null,

	@field:SerializedName("fakultas")
	val fakultas: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("jumlah_pasien")
	val jumlahPasien: Int? = null,

	@field:SerializedName("jumlah_pengalaman")
	val jumlahPengalaman: Int? = null,
)
