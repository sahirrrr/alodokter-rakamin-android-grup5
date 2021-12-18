package com.rakamin.alodokter.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.utils.USER_DATA
import com.rakamin.alodokter.databinding.FragmentEditProfileBinding
import org.koin.android.viewmodel.ext.android.viewModel

class EditProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProfile()

        binding?.btnSave?.setOnClickListener {
            val noHp = binding?.edtPhoneNumber?.text.toString().trim()
            val tglLahir = binding?.edtBirthday?.text.toString().trim()
            val kotaKab = binding?.edtCity?.text.toString().trim()

            val mBundle = Bundle()
            viewModel.putUserProfile("1", noHp, tglLahir, kotaKab).observe(viewLifecycleOwner, { putUserProfile ->
                if (putUserProfile != null) {
                    when(putUserProfile) {
                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            mBundle.putBoolean(USER_DATA, true)
                            Toast.makeText(requireContext(), "Your profile has been update!", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            mBundle.putBoolean(USER_DATA, false)
                            Toast.makeText(requireContext(), "Failed to update!", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                            Toast.makeText(requireContext(), "Loading..", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun showProfile() {
        viewModel.userProfile("1").observe(viewLifecycleOwner, { showProfile ->
            if (showProfile != null) {
                when(showProfile) {
                    is Resource.Success -> {
                        val dataArray = showProfile.data
                        if (dataArray != null) {
                            for (data in dataArray) {
                                binding?.edtName?.hint = data.nama
                                binding?.edtPhoneNumber?.hint = data.noHp
                                binding?.edtBirthday?.hint = data.tanggalLahir
                                binding?.edtCity?.hint = data.kabupatenKota
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding?.edtName?.hint = ""
                        binding?.edtPhoneNumber?.hint = ""
                        binding?.edtBirthday?.hint = ""
                        binding?.edtCity?.hint = ""
                        Toast.makeText(requireContext(), "Opps! something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> Toast.makeText(requireContext(), "Loading..", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}