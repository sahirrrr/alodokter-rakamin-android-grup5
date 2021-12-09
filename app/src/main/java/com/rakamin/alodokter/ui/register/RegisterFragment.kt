package com.rakamin.alodokter.ui.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding2.widget.RxTextView
import com.rakamin.alodokter.R
import com.rakamin.alodokter.databinding.FragmentRegisterBinding
import io.reactivex.Observable

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fullNameStream = RxTextView.textChanges(binding.edtFullname)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }
        fullNameStream.subscribe { it1 ->
            showFullnameEmptyAlert(it1)
        }

        val emailStream = RxTextView.textChanges(binding.edtEmail)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe { it2 ->
            showEmailExistAlert(it2)
        }

        val passwordStream = RxTextView.textChanges(binding.edtPassword)
            .skipInitialValue()
            .map { password ->
                password.length < 8
            }
        passwordStream.subscribe { it3 ->
            showPasswordMinimalAlert(it3)
        }

        val passwordConfirmStream = Observable.merge(
            RxTextView.textChanges(binding.edtPassword)
                .map { password ->
                    password.toString() != binding.edtConfirmPassword.text.toString()
                },
            RxTextView.textChanges(binding.edtConfirmPassword)
                .map { confirmPassword ->
                    confirmPassword.toString() != binding.edtPassword.text.toString()
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
            binding.btnRegister.isEnabled = isValid
        }
    }

    private fun showFullnameEmptyAlert(isNotValid: Boolean) {
        binding.edtFullname.error = if (isNotValid) getString(R.string.empty_field) else null
    }

    private fun showEmailExistAlert(isNotValid: Boolean) {
        binding.edtEmail.error = if (isNotValid) getString(R.string.email_not_valid) else null
    }

    private fun showPasswordMinimalAlert(isNotValid: Boolean) {
        binding.edtPassword.error = if (isNotValid) getString(R.string.password_minimal) else null
    }

    private fun showPasswordConfirmAlert(isNotValid: Boolean) {
        binding.edtConfirmPassword.error =
            if (isNotValid) getString(R.string.password_not_match) else null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
