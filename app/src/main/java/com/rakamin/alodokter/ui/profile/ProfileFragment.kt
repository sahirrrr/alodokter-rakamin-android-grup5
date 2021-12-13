package com.rakamin.alodokter.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rakamin.alodokter.R
import com.rakamin.alodokter.databinding.FragmentProfileBinding
import com.rakamin.alodokter.domain.model.LoginModel
import com.rakamin.alodokter.session.SessionRepository
import com.rakamin.alodokter.ui.login.LoginFragment
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding
    private val viewModel :ProfileViewModel by viewModel()
    private val sessionRepository: SessionRepository by inject()

    override fun onCreateView( inflater: LayoutInflater,  container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnLogout?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_logout -> {
                userLogout()
            }
        }
    }

private fun userLogout() {
    val builder = AlertDialog.Builder(requireContext())
    builder.setPositiveButton("YES") { _, _ ->
        binding?.progressBar?.visibility = View.VISIBLE
        val mBundle = Bundle()
        mBundle.putString("fromProfile", "FROM_PROFILE")
        findNavController().navigate(R.id.action_navigation_profile_to_loginFragment, mBundle)

        viewModel.userLogout()
        sessionRepository.logoutUser()

        Toast.makeText(requireContext(), "Logout Success", Toast.LENGTH_SHORT).show()
    }

    builder.setNegativeButton("Cancel"){_,_ -> }
    builder.setTitle("Logout From AloDokter?")
    builder.setMessage("You Can Login Back anytime you want")
    builder.create().show()
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Disposables.disposed()
    }
}