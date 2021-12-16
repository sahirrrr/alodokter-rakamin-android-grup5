package com.rakamin.alodokter.core.utils

import com.rakamin.alodokter.core.data.source.local.entity.ArticleEntity
import com.rakamin.alodokter.core.data.source.local.entity.RegisterEntity
import com.rakamin.alodokter.core.data.source.local.entity.UserEntity
import com.rakamin.alodokter.core.data.source.remote.response.*
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.model.RegisterModel
import com.rakamin.alodokter.domain.model.UserModel
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

    fun mapLoginResponseToEntities(data: LoginResponse): List<UserEntity> {
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

}