package com.rakamin.alodokter.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rakamin.alodokter.core.data.Resource
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.rakamin.alodokter.R
import com.rakamin.alodokter.databinding.FragmentProfileBinding
import com.rakamin.alodokter.session.SessionRepository
import io.reactivex.disposables.Disposables
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private val viewModel :ProfileViewModel by viewModel()
    private val sessionRepository: SessionRepository by inject()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView( inflater: LayoutInflater,  container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idUser = sessionRepository.getIdUser().toString()

        showProfile(idUser)
        
        binding?.btnLogout?.setOnClickListener {
            userLogout()
        }
    }

    private fun userLogout() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES") { _, _ ->
            binding?.progressBar?.visibility = View.VISIBLE
            findNavController().navigate(R.id.action_navigation_profile_to_loginFragment)

            viewModel.userLogout()
            sessionRepository.logoutUser()

            Toast.makeText(requireContext(), "Logout Success", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Cancel") {_,_ -> }
        builder.setTitle(getString(R.string.profile_logout))
        builder.setMessage(getString(R.string.profile_login_back_message))
        builder.create().show()
    }

    private fun showProfile(idUser: String) {
        viewModel.userProfile(idUser).observe(viewLifecycleOwner,{ showProfile ->
            if (showProfile != null) {
                when(showProfile) {
                    is Resource.Success -> {
                        val dataArray = showProfile.data
                        if (dataArray != null) {
                            for (data in dataArray) {
                                binding?.progressBar?.visibility = View.GONE
                                binding?.tvName?.text = data.nama
                                binding?.tvNumber?.text = data.noHp
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvName?.text = getString(R.string.guest_user)
                        binding?.tvNumber?.text = ""
                        Toast.makeText(requireContext(), getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
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
          Disposables.disposed()
      }
}


