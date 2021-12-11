package com.rakamin.alodokter.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.databinding.FragmentProfileBinding
import org.koin.android.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {
    private val viewModel : ProfileViewModel by viewModel()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedIstanceState: Bundle?) :View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProfile()



    }

    private fun showProfile() {
        viewModel.userProfile("1").observe(viewLifecycleOwner,{ showProfile ->
            if (showProfile != null) {
                when(showProfile) {
                    is Resource.Success ->{
                        val dataArray = showProfile.data
                        if (dataArray != null) {
                            for (data in dataArray) {
                                binding?.tvName?.text = data.nama
                                binding?.tvNumber?.text = data.noHp
                            }
                        }

                    }
                    is Resource.Error -> TODO()
                    is Resource.Loading -> TODO()

                }
            }
        })
    }


}


