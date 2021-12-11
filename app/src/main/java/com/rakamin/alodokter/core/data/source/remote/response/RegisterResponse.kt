package com.rakamin.alodokter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("user")
	val user: UserRegister,

	@field:SerializedName("token")
	val token: String
)

data class UserRegister(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String
)
