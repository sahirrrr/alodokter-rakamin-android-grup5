package com.rakamin.alodokter.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article_table")
data class ArticleEntity(
    @PrimaryKey
    val id: Int? = null,

    val penulis: String? = null,

    val foto: String? = null,

    val updatedAt: String? = null,

    val konten: String? = null,

    val createdAt: String? = null,


    val judul: String? = null
)