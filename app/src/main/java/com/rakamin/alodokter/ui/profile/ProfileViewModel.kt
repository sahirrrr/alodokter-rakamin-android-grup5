package com.rakamin.alodokter.ui.profile

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class ProfileViewModel (private val alodokterUseCase : AlodokterUseCase) : ViewModel() {

    fun userLogout() = alodokterUseCase.userLogout()

    fun userProfile(idUser : String) = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getProfile(idUser))

    fun putUserProfile(accessToken: String, idUser: String, name: String, noHp: String, tglLahir: String, kotaKab: String) = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.putUserProfile(accessToken, idUser, name, noHp, tglLahir, kotaKab))

    val getUserData = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getUserData())
}