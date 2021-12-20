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

        // supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary_pink)
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        // setContentView(R.layout.login_screen)

        setupNavigation()
    }

    fun setupNavigation() {
        /*val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)*/
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
/*
    fun loginWithBrowser() {
        var account = Auth0(
            "@string/auth0_clientId",
            "@string/auth0_domain"
        )

        // Setup the WebAuthProvider, using the custom scheme and scope
        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email")
            // Launch the authentication passing the callback where the results will be received
            .start(this, object : Callback<Credentials, AuthenticationException> {
                // Called when there is an authentication failure
                override fun onFailure(exception: AuthenticationException) {
                    // Something went wrong!
                    Log.i("LOGIN", exception.getDescription())
                    Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
                }

                // Called when authentication completed successfully
                override fun onSuccess(credentials: Credentials) {
                    // Get the access token from the credentials object.
                    // This can be used to call APIs
                    Log.i("LOGIN", "Login success")
                    MainActivity().cachedCredentials = credentials
                    navigateToHome()
                }
            })
    }
 */
}
