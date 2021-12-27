package com.example.hier

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hier.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary_pink)
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        setupNavigation()
    }

    private fun setupNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)
        hideBottomNavigationOnLoginPage(navController, bottomNavigationView)
    }

    private fun hideBottomNavigationOnLoginPage(
        navController: NavController,
        bottomNavigationView: BottomNavigationView
    ) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.visibility = if (destination.id == R.id.loginFragment) {
                supportActionBar?.hide()
                View.GONE
            } else {
                supportActionBar?.show()
                View.VISIBLE
            }
        }
    }
}
