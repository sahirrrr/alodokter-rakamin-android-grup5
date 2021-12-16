package com.rakamin.alodokter.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ForgotPasswordResponse(

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
) : Parcelable
