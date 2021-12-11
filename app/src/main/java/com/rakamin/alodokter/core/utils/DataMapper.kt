package com.rakamin.alodokter.core.utils

import com.rakamin.alodokter.core.data.source.local.entity.UserEntity
import com.rakamin.alodokter.core.data.source.remote.response.LoginResponse
import com.rakamin.alodokter.core.data.source.remote.response.ProfileResponse
import com.rakamin.alodokter.domain.model.UserModel
import java.util.ArrayList

object DataMapper {

    fun mapUserEntitiesToDomain(data : List<UserEntity>) : List<UserModel> {
        return data.map {
            with(it) {
                UserModel(
                    id, nama, email, jenisKelamin, umur, tanggalLahir, noHp, kabupatenKota, foto
                )
            }
        }
    }

    fun mapLoginResponseToEntities(data : LoginResponse) : List<UserEntity> {
        val userLogin = ArrayList<UserEntity>()
        with(data.user) {
            val user = UserEntity(
                this?.id,
                this?.nama,
                this?.email,
                this?.jenisKelamin,
                this?.umur,
                this?.tanggalLahir,
                this?.noHp,
                this?.kabupatenKota,
                this?.foto
            )
            userLogin.add(user)
        }
        return userLogin
    }

    fun mapProfileResponseToEntities(data : ProfileResponse) : List<UserEntity> {
        val userProfile = ArrayList<UserEntity>()
        with(data) {
            val user = UserEntity(
                this.id,
                this.nama,
                this.email,
                this.tanggalLahir,
                this.umur,
                this.jenisKelamin,
                this.noHp,
                this.foto,
                this.kabupatenKota,
            )
            userProfile.add(user)
        }
        return userProfile
    }


}