package com.rakamin.alodokter.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.databinding.FragmentLoginBinding
import com.rakamin.alodokter.session.SessionRepository
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel : LoginViewModel by viewModel()
    private val sessionRepository : SessionRepository by inject()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnLogin?.setOnClickListener {
            val email = binding?.edtEmail?.text.toString().trim()
            val password = binding?.edtPassword?.text.toString().trim()

            userLogin(email, password)
        }
    }

    private fun userLogin(email: String, password: String) {
        viewModel.userLogin(email, password).observe(viewLifecycleOwner, { userLogin ->
            if (userLogin != null) {
                when(userLogin) {
                    is Resource.Success -> {
                        val dataArray = userLogin.data
                        if (dataArray != null) {
                            for (data in dataArray) {
                                binding?.progressBar?.visibility = View.GONE
                                data.id?.let { sessionRepository.loginUser(it) }
                                val mBundle = Bundle()
                                Toast.makeText(
                                    requireContext(),
                                    "Login Successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(requireContext(), "Login failed!", Toast.LENGTH_SHORT).show()
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