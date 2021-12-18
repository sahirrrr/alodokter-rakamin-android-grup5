package com.rakamin.alodokter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListDoctorResponse(

	@field:SerializedName("data")
	val data: List<DoctorResponse>? = null,

)

data class DoctorResponse(

    @field:SerializedName("id")
    val id: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

    @field:SerializedName("spesialis")
    val spesialis: String? = null,

    @field:SerializedName("harga_konsul")
    val hargaKonsul: Int? = null,

    @field:SerializedName("rumah_sakit")
    val rumahSakit: String? = null,

    @field:SerializedName("foto")
    val foto: String? = null

)
