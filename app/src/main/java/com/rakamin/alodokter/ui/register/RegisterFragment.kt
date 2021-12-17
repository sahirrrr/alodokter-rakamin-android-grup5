package com.rakamin.alodokter.ui.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding2.widget.RxTextView
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.data.Resource
import com.rakamin.alodokter.core.utils.REGISTER_USER_STATUS
import com.rakamin.alodokter.databinding.FragmentRegisterBinding
import io.reactivex.Observable
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModel()

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding
    private var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fullNameStream = binding?.edtFullName?.let {
            RxTextView.textChanges(it)
                .skipInitialValue()
                .map { name ->
                    name.isEmpty()
                }
        }
        fullNameStream?.subscribe { it1 ->
            showFullNameEmptyAlert(it1)
        }

        val emailStream = binding?.edtEmail?.let {
            RxTextView.textChanges(it)
                .skipInitialValue()
                .map { email ->
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                }
        }
        emailStream?.subscribe { it2 ->
            showEmailExistAlert(it2)
        }

        val passwordStream = binding?.edtPassword?.let {
            RxTextView.textChanges(it)
                .skipInitialValue()
                .map { password ->
                    password.length < 8
                }
        }
        passwordStream?.subscribe { it3 ->
            showPasswordMinimalAlert(it3)
        }

        val passwordConfirmStream = Observable.merge(
            binding?.edtPassword?.let {
                RxTextView.textChanges(it)
                    .map { password ->
                        password.toString() != binding?.edtConfirmPassword?.text.toString()
                    }
            },
            binding?.edtConfirmPassword?.let {
                RxTextView.textChanges(it)
                    .map { confirmPassword ->
                        confirmPassword.toString() != binding?.edtPassword?.text.toString()
                    }
            }
        )
        passwordConfirmStream.subscribe { it4 ->
            showPasswordConfirmAlert(it4)
        }

        val invalidFieldStream = Observable.combineLatest(
            fullNameStream,
            emailStream,
            passwordStream,
            passwordConfirmStream,
            { t1: Boolean, t2: Boolean, t3: Boolean, t4: Boolean ->
                !t1 && !t2 && !t3 && !t4
            })

        invalidFieldStream.subscribe { isValid ->
            if (isValid) {
                binding?.btnRegister?.isEnabled = true
                binding?.btnRegister?.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.button))
            } else {
                binding?.btnRegister?.isEnabled = false
                binding?.btnRegister?.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.text_grey))
            }
        }

        binding?.btnRegister?.setOnClickListener {
            val fullName = binding?.edtFullName?.text.toString().trim()
            val email = binding?.edtEmail?.text.toString().trim()
            val password = binding?.edtPassword?.text.toString().trim()
            val passwordConfirmation = binding?.edtConfirmPassword?.text.toString().trim()

            val mBundle = Bundle()
            viewModel.userRegister(fullName, email, password, passwordConfirmation).observe(viewLifecycleOwner, { userRegister ->
                if (userRegister != null) {
                    when(userRegister) {
                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            mBundle.putBoolean(REGISTER_USER_STATUS, true)
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment, mBundle)
                        }
                        is Resource.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            mBundle.putBoolean(REGISTER_USER_STATUS, false)
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment, mBundle)
                        }
                        is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                    }
                }
            })
        }

        binding?.tvLogin?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun showFullNameEmptyAlert(isNotValid: Boolean) {
        binding?.edtFullName?.error = if (isNotValid) getString(R.string.empty_field) else null
    }

    private fun showEmailExistAlert(isNotValid: Boolean) {
        binding?.edtEmail?.error = if (isNotValid) getString(R.string.email_not_valid) else null
    }

    private fun showPasswordMinimalAlert(isNotValid: Boolean) {
        binding?.edtPassword?.error = if (isNotValid) getString(R.string.password_minimal) else null
    }

    private fun showPasswordConfirmAlert(isNotValid: Boolean) {
        binding?.edtConfirmPassword?.error = if (isNotValid) getString(R.string.password_not_match) else null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        root = null
    }
}
