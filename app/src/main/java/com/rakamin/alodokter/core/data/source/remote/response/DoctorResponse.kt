package com.rakamin.alodokter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DoctorResponse(

    @field:SerializedName("data")
    val data: List<DataDoctor?>? = null,

    @field:SerializedName("meta")
    val Jumlah: Jumlah? = null

)

data class DataDoctor(

    @field:SerializedName("id_doctor")
    val id: Int? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("spesialis")
    val spesialis: String? = null,

    @field:SerializedName("harga_konsul")
    val hargaKonsul: String? = null,

    @field:SerializedName("rating")
    val rating: String? = null,

    @field:SerializedName("lokasi")
    val lokasi: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,
)

data class Jumlah(
    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("count")
    val count: Int? = null
)