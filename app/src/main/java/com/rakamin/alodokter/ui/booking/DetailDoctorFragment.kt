package com.rakamin.alodokter.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.utils.Helper
import com.rakamin.alodokter.core.utils.ID_DOCTOR
import com.rakamin.alodokter.databinding.FragmentDetailDoctorBinding
import com.rakamin.alodokter.ui.adapter.ScheduleDoctorAdapter
import com.rakamin.alodokter.ui.adapter.TimeDoctorAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class DetailDoctorFragment: Fragment() {

    private val viewModel: DoctorViewModel by viewModel()
    private val scheduleDoctorAdapter = ScheduleDoctorAdapter()

    private var _binding: FragmentDetailDoctorBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailDoctorBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val doctorId = requireArguments().getString(ID_DOCTOR)
        if (doctorId != null) {
            doctorDetail(doctorId)
        }

        binding?.ivWhiteBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun doctorDetail(idDoctor: String) {
        viewModel.doctorProfile(idDoctor).observe(viewLifecycleOwner, { doctorProfile ->
            if (doctorProfile != null) {
                when(doctorProfile) {
                    is Resource.Success -> {
                        val dataArray = doctorProfile.data
                        if (dataArray != null) {
                            for (data in dataArray) {
                                val price = Helper.convertToCurrency(data.hargaKonsul)
                                binding?.progressBar?.visibility = View.GONE

                                binding?.tvDoctorName?.text = data.nama
                                binding?.tvSpesialisPrice?.text = getString(R.string.specialist_price, data.spesialis, price)

                                binding?.tvNumberPatient?.text = data.jumlahPasien.toString()
                                binding?.tvNumberExperience?.text = getString(R.string.experience_years ,data.jumlahPengalaman)
                                binding?.tvNumberRating?.text = data.rating?.toDouble().toString()

                                binding?.tvDescAboutDoctor?.text = data.about
                                binding?.tvLocationName?.text = data.rumahSakit
                                binding?.tvLocationAddress?.text = data.alamat

                                scheduleDoctorAdapter.setSchedule(data.schedule)
                                showRvSchedule()

                                binding?.tvUniversityName?.text = data.edukasi
                                binding?.tvMajor?.text = data.fakultas
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(requireContext(), "Failed to get detail doctor", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showRvSchedule() {
        with(binding?.rvSchedule) {
            this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            this?.setHasFixedSize(true)
            this?.adapter = scheduleDoctorAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}