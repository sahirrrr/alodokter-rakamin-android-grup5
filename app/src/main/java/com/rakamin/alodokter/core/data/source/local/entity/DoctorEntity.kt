package com.rakamin.alodokter.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor_table")
data class DoctorEntity (
    @PrimaryKey
    @ColumnInfo(name = "id_doctor")
    val id: Int? = null,

    val nama: String? = null,

    val spesialis: String? = null,

    val harga_konsul: String? = null,

    val rating: String? = null,

    val lokasi: String? = null,

    val createdAt: String? = null,

    val updatedAt: String? = null,


)