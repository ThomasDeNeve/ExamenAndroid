package com.example.hier

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.example.hier.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary_pink)
        var toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        //setContentView(R.layout.login_screen)

        setupNavigation()
        loginWithBrowser()
    }

    private fun loginWithBrowser() {

        // Setup the WebAuthProvider, using the custom scheme and scope.
        val account = Auth0(
            "tIHBlQvkYFg35zCofxtjKPvip5Yrg2b2",
            "devops-t4.eu.auth0.com"
        )
        Log.i("Test", "Print test.");

        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email")
            // Launch the authentication passing the callback where the results will be received
            .start(this, object : Callback<Credentials, AuthenticationException> {
                // Called when there is an authentication failure
                override fun onFailure(exception: AuthenticationException) {
                    // Something went wrong!
                    Log.i("LOGIN", "Login failed")
                }

                // Called when authentication completed successfully
                override fun onSuccess(credentials: Credentials) {
                    // Get the access token from the credentials object.
                    // This can be used to call APIs
                    Log.i("LOGIN", "Login success")
                    val accessToken = credentials.accessToken
                }
            })
    }

    private fun setupNavigation(){
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
}