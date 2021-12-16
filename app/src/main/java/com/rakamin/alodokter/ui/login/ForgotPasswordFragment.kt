package com.rakamin.alodokter.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding2.widget.RxTextView
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.source.remote.network.ApiResponse
import com.rakamin.alodokter.databinding.FragmentForgotPasswordBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : Fragment() {

    private val viewModel: ForgotPasswordViewModel by viewModel()

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding
    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailStream = binding?.edtEmail?.let {
            RxTextView.textChanges(it)
                .skipInitialValue()
                .map { email ->
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                }
        }
        emailStream?.subscribe { it ->
            showEmailExistAlert(it)
        }

        binding?.ivBack?.setOnClickListener {
            findNavController().navigateUp()
        }

        binding?.btnSendInstruction?.setOnClickListener {
            val email = binding?.edtEmail?.text.toString().trim()

            userForgotPassword(email)
        }
    }

    private fun userForgotPassword(email: String) {
        viewModel.userForgotPassword(email).observe(viewLifecycleOwner, { userForgotPassword ->
            if (userForgotPassword != null) {
                when (userForgotPassword) {
                    is ApiResponse.Success -> {
                        val response = userForgotPassword.data
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    is ApiResponse.Error -> {
                        Toast.makeText(
                            requireContext(),
                            userForgotPassword.errorMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun showEmailExistAlert(isNotValid: Boolean) {
        binding?.edtEmail?.error = if (isNotValid) getString(R.string.email_not_valid) else null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        root = null
    }
}