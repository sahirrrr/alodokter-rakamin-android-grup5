package com.rakamin.alodokter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("SearchResponse")
	val searchResponse: List<SearchResponseItem?>? = null
)

data class SearchResponseItem(

	@field:SerializedName("penulis")
	val penulis: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("konten")
	val konten: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("judul")
	val judul: String? = null
)
