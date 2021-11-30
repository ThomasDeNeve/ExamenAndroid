package com.example.hier.ui.profile

import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.UserProfile
import com.example.hier.MainActivity
import com.example.hier.R
import com.example.hier.databinding.FragmentProfileBinding
import com.example.hier.ui.login.LoginFragmentDirections
import com.example.hier.ui.reservations.ReservationsViewModel
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private lateinit var account: Auth0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ProfileViewModel by inject()
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        activity?.let {
            val clientId: String = it.getString(R.string.auth0_clientId)
            val domain: String = it.getString(R.string.auth0_domain)

            account = Auth0(clientId, domain)
        }

        //ToDo: Correctly get cashedCredentials from MainActivity in this method
        //setUserProfile()

        binding.lblName.text = "Test"

        binding.btnLogout.setOnClickListener{
            logoutWithBrowser()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Mijn profiel"
    }

    private fun setUserProfile() {
        val client = AuthenticationAPIClient(account)

        client.userInfo(MainActivity().cachedCredentials!!.accessToken!!)
            .start(object : Callback<UserProfile, AuthenticationException> {
                override fun onFailure(exception: AuthenticationException) {
                    Toast.makeText(context, "Laden van gebruikersinfo gefaald. ${exception.getDescription()}", Toast.LENGTH_LONG).show()
                }

                override fun onSuccess(profile: UserProfile) {
                    MainActivity().cachedUserProfile = profile
                }
            })
    }

    private fun logoutWithBrowser() {
        activity?.let {
            WebAuthProvider.logout(account)
                .withScheme(getString(R.string.auth0_scheme))
                .start(it, object : Callback<Void?, AuthenticationException> {
                    override fun onSuccess(payload: Void?) {
                        // The user has been logged out!
                        MainActivity().cachedCredentials = null
                        MainActivity().cachedUserProfile = null

                        navigateToLogin()
                    }

                    override fun onFailure(exception: AuthenticationException) {
                        Toast.makeText(context, "Logout failed", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    private fun navigateToLogin(){
        val directions = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
        findNavController().navigate(directions)
    }
}