package com.rakamin.alodokter.ui.booking

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.engine.Resource
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.utils.DataMapper
import com.rakamin.alodokter.databinding.FragmentListBookingDokterBinding
import com.rakamin.alodokter.ui.adapter.ListBookingDokterAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ListBookingDokterFragment : Fragment() {

    private val viewModel: ListBookingDokterViewModel by viewModel()
    private val doctorAdapter = ListBookingDokterAdapter()

    private var _binding: FragmentListBookingDokterBinding? = null
    private val binding get() = _binding
    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBookingDokterBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showDoctorList()
        doctorSearch()
    }

    private fun doctorSearch() {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val svListDoctor = binding?.svListDokter

        svListDoctor?.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

        svListDoctor?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                svListDoctor.clearFocus()
                if (query != null) {
                    search(query)
                    binding?.progressBar?.visibility = View.VISIBLE
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.empty_search),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun search(query: String) {
        viewModel.doctorSearch(query).observe(viewLifecycleOwner, { searchResult ->
            if (searchResult != null) {
                when (searchResult) {
                    is ApiResponse.Empty -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.fetch_doctor_empty),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    is ApiResponse.Error -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.fetch_doctor_empty),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        binding?.progressBar?.visibility = View.GONE
                    }
                    is ApiResponse.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        val result = searchResult.data
                        val newResult = DataMapper.mapDoctorSearchResponeToDomain(result)
                        doctorAdapter.setDoctor(newResult)
                        showRvBookingDokter()
                    }
                }
            }
        })
    }

    private fun showDoctorList(){
        viewModel.getDoctor().observe(viewLifecycleOwner, {doctor ->
            if (doctor != null) {
                when (doctor){
                    is com.rakamin.alodokter.core.data.Resource.Success -> {
                        val doctor = doctor.data
                            doctorAdapter.setDoctor(doctor)
                        binding?.progressBar?.visibility = View.GONE
                        showRvBookingDokter()
                    }
                    is com.rakamin.alodokter.core.data.Resource.Error ->{
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.fetch_doctor_empty),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    is  com.rakamin.alodokter.core.data.Resource.Loading -> binding?.progressBar?.visibility = View.GONE
                }
            }
        })
    }

    private fun showRvBookingDokter() {
        with(binding?.rvBookingDokter){
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = doctorAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}