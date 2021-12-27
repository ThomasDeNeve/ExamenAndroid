package com.example.hier.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.example.hier.MyApplication
import com.example.hier.MyApplication.Companion.apiAccessToken
import com.example.hier.MyApplication.Companion.cachedCredentials
import com.example.hier.R
import com.example.hier.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {

    private lateinit var account: Auth0
    val viewModel: LoginViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginButton.setOnClickListener {
            getAPIAccessToken()
        }

        return binding.root
    }

    private fun getAPIAccessToken() {
        // Setup the WebAuthProvider, using the custom scheme and scope
        val clientId: String = getString(R.string.auth0_clientId)
        val domain: String = getString(R.string.auth0_domain)
        account = Auth0(clientId, domain)

        activity?.let {
            // Get Authentication token for API calls
            WebAuthProvider.login(account)
                .withScheme("demo")
                .withScope("openid")
                .withAudience("http://ec2-3-212-186-23.compute-1.amazonaws.com:5000/API/")
                // Launch the authentication passing the callback where the results will be received
                .start(
                    it,
                    object : Callback<Credentials, AuthenticationException> {
                        // Called when there is an authentication failure
                        override fun onFailure(error: AuthenticationException) {
                            // Something went wrong!
                            Log.i("LOGIN", error.getDescription())
                            Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
                        }

                        // Called when authentication completed successfully
                        override fun onSuccess(result: Credentials) {
                            // Get the access token from the credentials object.
                            // This can be used to call APIs
                            apiAccessToken = result.accessToken
                            loginWithBrowser()
                        }
                    }
                )
        }
    }

    private fun loginWithBrowser() {
        activity?.let {
            WebAuthProvider.login(account)
                .withScheme("demo")
                .withScope("openid profile email read:current_user update:current_user_metadata")
                .withAudience("https://${getString(R.string.auth0_domain)}/api/v2/")
                // Launch the authentication passing the callback where the results will be received
                .start(
                    it,
                    object : Callback<Credentials, AuthenticationException> {
                        // Called when there is an authentication failure
                        override fun onFailure(error: AuthenticationException) {
                            // Something went wrong!
                            Log.i("LOGIN", error.getDescription())
                            Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
                        }

                        // Called when authentication completed successfully
                        override fun onSuccess(result: Credentials) {
                            // Get the access token from the credentials object.
                            // This can be used to call APIs
                            Log.i("LOGIN", "Login success")
                            cachedCredentials = result
                            getUserProfile()
                        }
                    }
                )
        }
    }

    private fun getUserProfile() {
        if (MyApplication.cachedUserProfile != null) {
            return
        }

        val client = AuthenticationAPIClient(account)

        client.userInfo(cachedCredentials!!.accessToken)
            .start(object : Callback<UserProfile, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                    Toast.makeText(
                        context,
                        "Laden van gebruikersinfo gefaald. ${error.getDescription()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onSuccess(result: UserProfile) {
                    MyApplication.cachedUserProfile = result
                    lifecycleScope.launch {
                        try {
                            viewModel.insertNewUser(result.email)
                            navigateToHome()
                        } catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
    }

    private fun navigateToHome() {
        val directions =
            LoginFragmentDirections.actionLoginFragmentToChoiceMeetingRoomFragment()
        findNavController().navigate(directions)
    }
}
