package com.rakamin.alodokter.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.utils.EXTRA_DATA
import com.rakamin.alodokter.databinding.FragmentHomeBinding
import com.rakamin.alodokter.session.SessionRepository
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val sessionRepository : SessionRepository by inject()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var idUser = sessionRepository.getIdUser()
        if (idUser == 0) idUser = requireArguments().getInt(EXTRA_DATA)

        binding?.tvName?.text = idUser.toString()
        //Back press Close App
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // handle back event
            activity?.finishAndRemoveTask()
        }
    }
}