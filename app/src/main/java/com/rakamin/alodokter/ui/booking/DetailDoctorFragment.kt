package com.rakamin.alodokter.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.utils.Helper
import com.rakamin.alodokter.databinding.FragmentDetailDoctorBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailDoctorFragment: Fragment() {

    private val viewModel: DoctorViewModel by viewModel()

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

        doctorDetail("1")
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
                                binding?.tvNumberExperience?.text = data.jumlahPengalaman.toString()
                                binding?.tvNumberRating?.text = data.rating.toString()

                                binding?.tvDescAboutDoctor?.text = data.about
                                binding?.tvLocationName?.text = data.lokasi
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}