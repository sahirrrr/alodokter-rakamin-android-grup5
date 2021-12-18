package com.rakamin.alodokter.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.databinding.FragmentEditProfileBinding
import com.rakamin.alodokter.session.SessionRepository
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class EditProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()
    private val sessionRepository: SessionRepository by inject()

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

        val idUser = sessionRepository.getIdUser()
        val token = sessionRepository.getTokenUser()

        showProfile(token.toString(), idUser)
    }

    private fun showProfile(token: String, idUser: Int) {
        viewModel.userProfile(idUser.toString()).observe(viewLifecycleOwner, { showProfile ->
            if (showProfile != null) {
                when(showProfile) {
                    is Resource.Success -> {
                        val dataArray = showProfile.data
                        if (dataArray != null) {
                            for (data in dataArray) {
                                binding?.progressBar?.visibility = View.GONE
                                binding?.edtName?.setText(data.nama)
                                binding?.edtPhoneNumber?.setText(data.noHp)
                                binding?.edtBirthday?.setText(data.tanggalLahir)
                                binding?.edtCity?.setText(data.kabupatenKota)
                                binding?.ivEditProfile?.let {
                                    if (data.foto == null) {
                                        it.setImageResource(R.drawable.ic_profile_photo)
                                    } else {
                                        Glide.with(this)
                                            .load(data.foto)
                                            .into(it)
                                    }
                                }

                                binding?.btnSave?.setOnClickListener {
                                    val name = binding?.edtName?.text.toString().trim()
                                    val noHp = binding?.edtPhoneNumber?.text.toString().trim()
                                    val tglLahir = binding?.edtBirthday?.text.toString().trim()
                                    val kotaKab = binding?.edtCity?.text.toString().trim()

                                    var newToken = data.token ?: token

                                    if (newToken == null) {
                                        viewModel.getUserData.observe(viewLifecycleOwner, {userData ->
                                            if (userData != null) {
                                                for (data in userData) {
                                                    newToken = data.token.toString()
                                                }
                                            }
                                        })
                                    }

                                    viewModel.putUserProfile(newToken, data.id.toString(), name, noHp, tglLahir, kotaKab).observe(viewLifecycleOwner, { putUserProfile ->
                                        if (putUserProfile != null) {
                                            when(putUserProfile) {
                                                is Resource.Success -> {
                                                    binding?.progressBar?.visibility = View.GONE
                                                    Toast.makeText(requireContext(), "Your profile has been update!", Toast.LENGTH_SHORT).show()
                                                }
                                                is Resource.Error -> {
                                                    binding?.progressBar?.visibility = View.GONE
                                                    Toast.makeText(requireContext(), "Failed to update!", Toast.LENGTH_SHORT).show()
                                                }
                                                is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                                            }
                                        }
                                    })
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        findNavController().navigateUp()
                        Toast.makeText(requireContext(), "Opps! something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

}