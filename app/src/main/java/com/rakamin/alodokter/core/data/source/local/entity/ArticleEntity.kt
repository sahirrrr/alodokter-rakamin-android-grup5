package com.rakamin.alodokter.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class ArticleEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_article")
    val id: Int? = null,

    val penulis: String? = null,

    val foto: String? = null,

    val updatedAt: String? = null,

    val konten: String? = null,

    val createdAt: String? = null,

    @ColumnInfo(name = "judul")
    val judul: String? = null,

    val kategori: String? = null
)
