package com.rakamin.alodokter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailDoctorResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("about")
	val about: String? = null,

	@field:SerializedName("spesialis")
	val spesialis: String? = null,

	@field:SerializedName("harga_konsul")
	val hargaKonsul: Int? = null,

	@field:SerializedName("jumlah_pasien")
	val jumlahPasien: Int? = null,

	@field:SerializedName("jumlah_pengalaman")
	val jumlahPengalaman: Int? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("rumah_sakit")
	val rumahSakit: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("docday")
	val schedule: List<ScheduleItem>? = null,

	@field:SerializedName("edukasi")
	val edukasi: String? = null,

	@field:SerializedName("fakultas")
	val fakultas: String? = null,

	@field:SerializedName("jurusan")
	val jurusan: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null
)

data class ScheduleItem(

	@field:SerializedName("hari")
	val hari: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("doctime")
	val docTime: List<DoctorTimeItem>? = null,
)

data class DoctorTimeItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("jam")
	val jam: String? = null
)
