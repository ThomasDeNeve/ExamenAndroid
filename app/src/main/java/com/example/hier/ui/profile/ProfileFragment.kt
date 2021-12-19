package com.example.hier.ui.profile

import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.management.ManagementException
import com.auth0.android.management.UsersAPIClient
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.UserProfile
import com.example.hier.MyApplication.Companion.cachedCredentials
import com.example.hier.MyApplication.Companion.cachedUserProfile
import com.example.hier.R
import com.example.hier.databinding.FragmentProfileBinding
import com.example.hier.ui.login.LoginFragmentDirections
import com.example.hier.ui.reservations.ReservationsViewModel
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private lateinit var account: Auth0
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ProfileViewModel by inject()
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        activity?.let {
            val clientId: String = getString(R.string.auth0_clientId)
            val domain: String = getString(R.string.auth0_domain)

            account = Auth0(clientId, domain)
        }

        getUserMetadata()

        //binding.lblName.text = "Test"

        binding.btnLogout.setOnClickListener{
            logoutWithBrowser()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Mijn profiel"
    }

    private fun getUserMetadata() {
        // Guard against getting the metadata when no user is logged in
        if (cachedCredentials == null) {
            return
        }

        val usersClient = UsersAPIClient(account, cachedCredentials!!.accessToken!!)

        usersClient
            .getProfile(cachedUserProfile!!.getId()!!)
            .start(object : Callback<UserProfile, ManagementException> {

                override fun onFailure(exception: ManagementException) {
                    Toast.makeText(context, "Laden van gebruikersinfo gefaald. ${exception.getDescription()}", Toast.LENGTH_LONG).show()
                }

                override fun onSuccess(userProfile: UserProfile) {
                    cachedUserProfile = userProfile

                    binding.lblProfileName.text = userProfile.getUserMetadata()["first_name"] as String?
                    binding.lblProfileFirstName.text = userProfile.getUserMetadata()["last_name"] as String?
                    binding.lblProfileEmail.text = userProfile.email
                    binding.lblProfileTel.text = userProfile.getUserMetadata()["tel"] as String?
                    binding.lblProfileBTW.text = userProfile.getUserMetadata()["btw_nr"] as String?
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
                        cachedCredentials = null
                        cachedUserProfile = null

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