package com.example.hier.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.example.hier.MainActivity
import com.example.hier.R
import com.example.hier.databinding.FragmentLoginBinding
import com.example.hier.util.Status
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {

    private lateinit var account: Auth0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: LoginViewModel by inject()
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.loginButton.setOnClickListener{
//             Toast.makeText(context, "login successful", Toast.LENGTH_LONG).show()
//            Log.e("test", "lalala")

            //var username = binding.editTextTextPersonName.text.toString()
            //var password = binding.editTextTextPassword.text.toString()

            //viewModel.setCredentials(username, password)

            loginWithBrowser()
        }

        //TODO find out why this doesn't work
        viewModel.loginResponse.observe(viewLifecycleOwner,
            Observer {
                //Log.e("LoginFragment", it.toString())
                it?.let { resource ->
                    when (resource.status) {
                        //TODO add logic for logging in
                        Status.SUCCESS -> {
                            Toast.makeText(context, "login successful", Toast.LENGTH_LONG).show()
                            //Log.e("Fragment", "successful fetch from Fragment")
                            navigateToHome()
                        }
                        Status.LOADING -> {
                            Toast.makeText(context, "loading", Toast.LENGTH_LONG).show()
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        )

        return binding.root
    }

    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope
        activity?.let {
            val clientId: String = it.getString(R.string.auth0_clientId)
            val domain: String = it.getString(R.string.auth0_domain)

            account = Auth0(clientId, domain)

            WebAuthProvider.login(account)
                .withScheme("demo")
                .withScope("openid profile email")
                // Launch the authentication passing the callback where the results will be received
                .start(it, object : Callback<Credentials, AuthenticationException> {
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
                        MainActivity().cachedCredentials = result
                        navigateToHome()
                    }
                })
        }
    }

    private fun navigateToHome(){
        val directions = LoginFragmentDirections.actionLoginFragmentToMainActivity()
        findNavController().navigate(directions)
    }
}