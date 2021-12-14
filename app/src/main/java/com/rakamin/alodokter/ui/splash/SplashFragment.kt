package com.rakamin.alodokter.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rakamin.alodokter.R
import com.rakamin.alodokter.session.SessionRepository
import org.koin.android.ext.android.inject


class SplashFragment : Fragment() {

    private val sessionRepository: SessionRepository by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Handler(Looper.getMainLooper()).postDelayed({
            if (sessionRepository.isOnBoardingFinish()) {
                if (sessionRepository.isLogin()) {
                    findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
            }
        }, 3000)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}