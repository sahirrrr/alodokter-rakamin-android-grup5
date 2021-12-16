package com.rakamin.alodokter.ui.booking

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class DoctorViewModel(private val alodokterUseCase: AlodokterUseCase): ViewModel() {

    fun doctorProfile(idDoctor: String) = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getDoctorDetail(idDoctor))
}