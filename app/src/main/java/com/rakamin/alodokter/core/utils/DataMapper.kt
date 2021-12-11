package com.rakamin.alodokter.core.utils

import com.rakamin.alodokter.core.data.source.local.entity.ArticleEntity
import com.rakamin.alodokter.core.data.source.local.entity.LoginEntity
import com.rakamin.alodokter.core.data.source.remote.response.ArticleResponse
import com.rakamin.alodokter.core.data.source.remote.response.LoginResponse
import com.rakamin.alodokter.domain.model.ArticleModel
import com.rakamin.alodokter.domain.model.LoginModel
import java.util.ArrayList

object DataMapper {

    fun mapLoginEntitiesToDomain(data: List<LoginEntity>): List<LoginModel> {
        return data.map {
            with(it) {
                LoginModel(
                    id, nama, email, jenisKelamin, umur, tanggalLahir, noHp, kabupatenKota, foto
                )
            }
        }
    }

    fun mapLoginResponseToEntities(data: LoginResponse): List<LoginEntity> {
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

    fun mapArticleEntitiesToDomain(data: List<ArticleEntity>): List<ArticleModel> {
        return data.map {
            ArticleModel(
                it.id,
                it.penulis,
                it.foto,
                it.updatedAt,
                it.konten,
                it.createdAt,
                it.judul
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
                    it?.judul
                )
                listArticle.add(article)
            }
        }
        return listArticle


    }
}