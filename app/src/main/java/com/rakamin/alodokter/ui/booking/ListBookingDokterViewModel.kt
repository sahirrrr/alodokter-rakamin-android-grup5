package com.rakamin.alodokter.ui.booking

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.room.Query
import com.rakamin.alodokter.domain.usecase.AlodokterUseCase

class ListBookingDokterViewModel (private val alodokterUseCase: AlodokterUseCase) : ViewModel() {

    fun getDoctor() = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getDoctor())

    fun getDoctorById(id: Int) = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.getDoctorById(id))

    fun doctorSearch(query: String) = LiveDataReactiveStreams.fromPublisher(alodokterUseCase.searchDoctor(query))
}