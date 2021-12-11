package com.rakamin.alodokter.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rakamin.alodokter.R
import com.rakamin.alodokter.databinding.FragmentOnBoardingBinding
import com.rakamin.alodokter.session.SessionRepository
import org.koin.android.ext.android.inject

class OnBoardingFragment : Fragment() {

    private val sessionRepository : SessionRepository by inject()

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding
    private var root: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnGetStarted?.setOnClickListener {
            sessionRepository.onBoarding()
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }

}