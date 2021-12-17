package com.rakamin.alodokter

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rakamin.alodokter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // hide action bar
        supportActionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        val navController = findNavController(R.id.nav_host_home_fragment)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(
                destination.id == R.id.splashFragment ||
                destination.id == R.id.onBoardingFragment ||
                destination.id == R.id.loginFragment ||
                destination.id == R.id.registerFragment ||
                destination.id == R.id.detailDoctorFragment ||
                destination.id == R.id.articleFragment ||
                destination.id == R.id.detailArticleFragment||
                destination.id == R.id.forgotPasswordFragment ||
                destination.id == R.id.editProfileFragment
            ) {
                navView.visibility = View.GONE
                window.statusBarColor = Color.TRANSPARENT
                setLightStatusBar(true)
            } else {
                navView.visibility = View.VISIBLE
                window.statusBarColor = Color.TRANSPARENT
                setLightStatusBar(true)
            }
            
            when (destination.id) {
                R.id.detailArticleFragment -> {
                    window.statusBarColor = Color.TRANSPARENT
                    setLightStatusBar(false)
                }

                R.id.splashFragment -> {
                    window.statusBarColor = getColor(R.color.primary)
                    setLightStatusBar(false)
                }
            }
        }
    }

    private fun setLightStatusBar(status: Boolean) {
        if (status){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window?.insetsController?.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window?.insetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
            }
        }
    }

    private fun showStatusBar(status: Boolean){
        if(status) {
            WindowCompat.setDecorFitsSystemWindows(window, true)
        }
        else {
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}