package com.rakamin.alodokter.ui.booking

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.core.utils.DataMapper
import com.rakamin.alodokter.core.utils.EXTRA_QUERY
import com.rakamin.alodokter.databinding.FragmentListBookingDoctorBinding
import com.rakamin.alodokter.ui.adapter.ListBookingDokterAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ListBookingDoctorFragment : Fragment() {

    private val viewModel: DoctorViewModel by viewModel()
    private val doctorAdapter = ListBookingDokterAdapter()

    private var _binding: FragmentListBookingDoctorBinding? = null
    private val binding get() = _binding
    private var root: View? = null

    private var pressedTime: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListBookingDoctorBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val query = requireArguments().getString(EXTRA_QUERY)
            if (query != null) {
                search(query)
            }
        } else {
            doctorSearch()
            showDoctorList()
        }

        //Back press Close App
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // handle back event
            if (pressedTime + 5000 > System.currentTimeMillis()) {
                activity?.finishAndRemoveTask()
            } else {
                Toast.makeText(requireContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            pressedTime = System.currentTimeMillis()
        }
    }

    private fun doctorSearch() {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val svListDoctor = binding?.svDoctor

        svListDoctor?.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

        svListDoctor?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                svListDoctor.clearFocus()
                if (query != null) {
                    search(query)
                } else {
                    Toast.makeText(requireContext(), getString(R.string.empty_search), Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun search(query: String) {
        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.doctorSearch(query).observe(viewLifecycleOwner, { searchResult ->
            if (searchResult != null) {
                when (searchResult) {
                    is ApiResponse.Empty -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.rvBookingDokter?.visibility = View.GONE
                        binding?.tvEmptyStateDoctorDesc?.text = getString(R.string.empty_state_doctor_empty_desc)
                        binding?.ivEmptyStateDoctor?.visibility = View.VISIBLE
                        binding?.tvEmptyStateDoctorDesc?.visibility = View.VISIBLE
                    }
                    is ApiResponse.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.rvBookingDokter?.visibility = View.GONE
                        binding?.tvEmptyStateDoctorDesc?.text = getString(R.string.empty_state_doctor_error_desc)
                        binding?.ivEmptyStateDoctor?.visibility = View.VISIBLE
                        binding?.tvEmptyStateDoctorDesc?.visibility = View.VISIBLE
                    }
                    is ApiResponse.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.ivEmptyStateDoctor?.visibility = View.GONE
                        binding?.tvEmptyStateDoctorDesc?.visibility = View.GONE
                        val result = searchResult.data
                        val newResult = DataMapper.mapDoctorSearchResponseToDomain(result)
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
                when (doctor) {
                    is Resource.Success -> {
                        val doctorData = doctor.data
                        doctorAdapter.setDoctor(doctorData)
                        binding?.progressBar?.visibility = View.GONE
                        showRvBookingDokter()
                    }
                    is Resource.Error ->{
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvEmptyStateDoctorDesc?.text = getString(R.string.empty_state_doctor_error_desc)
                        binding?.ivEmptyStateDoctor?.visibility = View.VISIBLE
                        binding?.tvEmptyStateDoctorDesc?.visibility = View.VISIBLE
                    }
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
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