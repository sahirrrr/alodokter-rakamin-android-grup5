package com.rakamin.alodokter.core.utils

import com.rakamin.alodokter.core.data.source.local.entity.LoginEntity
import com.rakamin.alodokter.core.data.source.local.entity.ProfileEntity
import com.rakamin.alodokter.core.data.source.remote.response.LoginResponse
import com.rakamin.alodokter.core.data.source.remote.response.ProfileResponse
import com.rakamin.alodokter.domain.model.LoginModel
import com.rakamin.alodokter.domain.model.ProfileModel
import java.util.ArrayList

object DataMapper {

    fun mapLoginEntitiesToDomain(data : List<LoginEntity>) : List<LoginModel> {
        return data.map {
            with(it) {
                LoginModel(
                    id, nama, email, jenisKelamin, umur, tanggalLahir, noHp, kabupatenKota, foto
                )
            }
        }
    }

    fun mapLoginResponseToEntities(data : LoginResponse) : List<LoginEntity> {
        val userLogin = ArrayList<LoginEntity>()
        with(data.user) {
            val user = LoginEntity(
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
    fun mapProfileEntitiesToDomain(data : List<ProfileEntity>) : List<ProfileModel> {
        return data.map{
            with(it) {
                ProfileModel(
                    id, nama, email, tanggalLahir, umur, jenisKelamin, noHp, foto, kabupatenKota
                )
            }
        }
    }
    fun mapProfileResponseToEntities(data : ProfileResponse) : List<ProfileEntity> {
        val userProfile = ArrayList<ProfileEntity>()
        with(data) {
            val user = ProfileEntity(
                this?.id,
                this?.nama,
                this?.email,
                this?.tanggalLahir,
                this?.umur,
                this?.jenisKelamin,
                this?.noHp,
                this?.foto,
                this?.kabupatenKota,
            )
            userProfile.add(user)
        }
        return userProfile
    }


}