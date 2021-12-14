package com.rakamin.alodokter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rakamin.alodokter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

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
            if(destination.id == R.id.splashFragment || destination.id == R.id.loginFragment || destination.id == R.id.registerFragment ||  destination.id == R.id.onBoardingFragment) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}