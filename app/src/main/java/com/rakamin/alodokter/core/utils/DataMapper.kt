package com.rakamin.alodokter.core.utils

import com.rakamin.alodokter.core.data.source.local.entity.*
import com.rakamin.alodokter.core.data.source.remote.response.*
import com.rakamin.alodokter.domain.model.*
import java.util.ArrayList
import com.rakamin.alodokter.core.data.source.remote.response.*

import java.util.*

object DataMapper {

    fun mapUserEntitiesToDomain(data: List<UserEntity>): List<UserModel> {
        return data.map {
            with(it) {
                UserModel(
                    id, nama, email, jenisKelamin, umur, tanggalLahir, noHp, kabupatenKota, foto
                )
            }
        }
    }

    fun mapEditProfileResponseToEntities(data : LoginResponse): List<UserEntity> {
        val editUserProfile = ArrayList<UserEntity>()
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
            editUserProfile.add(user)
        }
        return editUserProfile
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

    fun mapRegisterEntitiesToDomain(data: List<RegisterEntity>): List<RegisterModel> {
        return data.map {
            with(it) {
                RegisterModel(
                    id, email, token
                )
            }
        }
    }

    fun mapRegisterResponseToEntities(data: RegisterResponse): List<RegisterEntity> {
        val userRegister = ArrayList<RegisterEntity>()
        with(data) {
            val user = RegisterEntity(
                this.user.id,
                this.user.email,
                this.token
            )
            userRegister.add(user)
        }
        return userRegister
    }

    fun mapProfileResponseToEntities(data: ProfileResponse): List<UserEntity> {
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

    fun mapArticleEntitiesToDomain(data: List<ArticleEntity>): List<ArticleModel> {
        return data.map {
            ArticleModel(
                it.id,
                it.penulis,
                it.foto,
                it.updatedAt,
                it.konten,
                it.createdAt,
                it.judul,
                it.kategori
            )
        }
    }

    fun mapArticleResponseToArticleEntities(data: ArticleResponse): List<ArticleEntity> {
        val listArticle = ArrayList<ArticleEntity>()
        with(data.data) {
            this?.map {
                val article = ArticleEntity(
                    it?.id,
                    it?.penulis,
                    it?.foto,
                    it?.updatedAt,
                    it?.konten,
                    it?.createdAt,
                    it?.judul,
                    it?.kategori
                )
                listArticle.add(article)
            }
            return listArticle
        }
    }

    fun mapArticleSearchResponseToDomain(data: List<ArticleSearchResponse>): List<ArticleModel> {
        return data.map {
            with(it) {
                ArticleModel(
                    id, penulis, foto, updatedAt, konten, createdAt, judul,kategori
                )
            }
        }
    }

    fun mapDoctorEntitiesToDomain(data: List<ListDoctorEntity>): List<ListDoctorModel>{
        return data.map {
            ListDoctorModel(
                it.id,
                it.nama,
                it.spesialis,
                it.rumahSakit,
                it.hargaKonsul,
                it.foto
            )
        }
    }

    fun mapDoctorResponseToDoctorEntities(data: ListDoctorResponse): List<ListDoctorEntity>{
        val listDoctor = ArrayList<ListDoctorEntity>()
        with(data.data) {
            this?.map {
                val doctor = ListDoctorEntity(
                    it.id,
                    it.nama,
                    it.spesialis,
                    it.rumahSakit,
                    it.hargaKonsul,
                    it.foto
                )
                listDoctor.add(doctor)
            }
        }
        return listDoctor
    }

    fun mapDetailDoctorEntitiesToDomain(data: List<DetailDoctorEntity>) : List<DetailDoctorModel> {
        return data.map {
            with(it) {
                DetailDoctorModel(
                    id,
                    nama,
                    about,
                    spesialis,
                    hargaKonsul,
                    jumlahPasien,
                    jumlahPengalaman,
                    rating,
                    lokasi,
                    rumahSakit,
                    alamat,
                    schedule,
                    edukasi,
                    fakultas,
                    jurusan,
                    foto
                )
            }
        }
    }

    fun mapDetailDoctorResponseToEntities(data: DetailDoctorResponse) : List<DetailDoctorEntity> {
        val doctorProfile = ArrayList<DetailDoctorEntity>()
        with(data) {
            val doctor = DetailDoctorEntity(
                this.id,
                this.nama,
                this.about,
                this.spesialis,
                this.hargaKonsul,
                this.jumlahPasien,
                this.jumlahPengalaman,
                this.rating,
                this.lokasi,
                this.rumahSakit,
                this.alamat,
                this.schedule,
                this.edukasi,
                this.fakultas,
                this.jurusan,
                this.foto
            )
            doctorProfile.add(doctor)
        }
        return doctorProfile
    }


    fun mapDoctorSearchResponseToDomain(data: List<DoctorResponse>): List<ListDoctorModel>{
        return data.map {
            with(it) {
                ListDoctorModel(
                    id, nama, spesialis, rumahSakit, hargaKonsul, foto
                )
            }
        }
    }
}