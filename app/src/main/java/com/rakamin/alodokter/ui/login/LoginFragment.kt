package com.rakamin.alodokter.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.utils.EXTRA_DATA
import com.rakamin.alodokter.core.utils.REGISTER_USER_STATUS
import com.rakamin.alodokter.core.utils.TAG_STATUS_DIALOG
import com.rakamin.alodokter.databinding.FragmentLoginBinding
import com.rakamin.alodokter.session.SessionRepository
import com.rakamin.alodokter.ui.dialog.StatusDialogFragment
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

        if (arguments != null) {
            val registerStatus = requireArguments().getBoolean(REGISTER_USER_STATUS)
            showDialogStatus(REGISTER_USER_STATUS, registerStatus)
        }

        binding?.btnLogin?.setOnClickListener {
            val email = binding?.edtEmail?.text.toString().trim()
            val password = binding?.edtPassword?.text.toString().trim()

            userLogin(email, password)
        }

        binding?.tvForgotPassword?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding?.tvRegister?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding?.tvSkipLogin?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        //Back press Close App
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // handle back event
            activity?.finishAndRemoveTask()
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
                                val mBundle = Bundle()
                                data.id?.let { mBundle.putInt(EXTRA_DATA, it) }
                                data.id?.let { sessionRepository.loginUser(it) }
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment, mBundle)
                                Toast.makeText(requireContext(), "Login Successfully!", Toast.LENGTH_SHORT).show()
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

    private fun showDialogStatus(key: String, value: Boolean) {
        val mBundle = Bundle()
        val dialog = StatusDialogFragment()
        mBundle.putBoolean(key, value)
        dialog.arguments = mBundle
        dialog.show(childFragmentManager, TAG_STATUS_DIALOG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        root = null
    }
}