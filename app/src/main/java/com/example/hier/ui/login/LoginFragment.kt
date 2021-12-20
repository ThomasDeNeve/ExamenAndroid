package com.example.hier.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
import com.example.hier.MyApplication.Companion.cachedCredentials
import com.example.hier.R
import com.example.hier.databinding.FragmentLoginBinding
import com.example.hier.util.Status
import org.koin.android.ext.android.inject
import com.example.hier.repository.UserRepository
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var account: Auth0
    val viewModel: LoginViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val viewModel: LoginViewModel by inject()
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginButton.setOnClickListener{
            loginWithBrowser()
        }

        return binding.root
    }

    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope
        activity?.let {
            val clientId: String = getString(R.string.auth0_clientId)
            val domain: String = getString(R.string.auth0_domain)

            account = Auth0(clientId, domain)

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
                    })
        }
    }

    private fun getUserProfile() {
        if (MyApplication.cachedUserProfile != null) {
            return
        }

        val client = AuthenticationAPIClient(account)

        client.userInfo(cachedCredentials!!.accessToken!!)
            .start(object : Callback<UserProfile, AuthenticationException> {
                override fun onFailure(exception: AuthenticationException) {
                    Toast.makeText(context, "Laden van gebruikersinfo gefaald. ${exception.getDescription()}", Toast.LENGTH_LONG).show()
                }

                override fun onSuccess(userProfile: UserProfile) {
                    MyApplication.cachedUserProfile = userProfile
                    lifecycleScope.launch { viewModel.insertNewUser(userProfile.email) }
                    navigateToHome()
                }
            })
    }

    private fun navigateToHome(){
        val directions = LoginFragmentDirections.actionLoginFragmentToChoiceMeetingRoomFragment()
        findNavController().navigate(directions)
    }
}
