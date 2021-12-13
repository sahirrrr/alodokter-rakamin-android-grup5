package com.rakamin.alodokter.ui.profile

import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class ProfileViewModel (private val alodokterUseCase : AlodokterUseCase) : ViewModel() {

    fun userLogout() = alodokterUseCase.userLogout()
}