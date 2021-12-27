package com.example.hier.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.management.ManagementException
import com.auth0.android.management.UsersAPIClient
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.UserProfile
import com.example.hier.MainActivity
import com.example.hier.MyApplication.Companion.cachedCredentials
import com.example.hier.MyApplication.Companion.cachedUserProfile
import com.example.hier.R
import com.example.hier.databinding.FragmentProfileBinding
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private lateinit var account: Auth0
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        binding.btnLogout.setOnClickListener {
            logoutWithBrowser()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            (activity as MainActivity).supportActionBar?.title = "Mijn profiel"
        } catch (e: java.lang.ClassCastException) {
            Log.i("classcastexception: ", e.stackTraceToString())
        }
    }

    private fun getUserMetadata() {
        // Guard against getting the metadata when no user is logged in
        if (cachedCredentials == null) {
            return
        }

        val usersClient = UsersAPIClient(account, cachedCredentials!!.accessToken)

        usersClient
            .getProfile(cachedUserProfile!!.getId()!!)
            .start(object : Callback<UserProfile, ManagementException> {

                override fun onFailure(error: ManagementException) {
                    Toast.makeText(
                        context,
                        "Laden van gebruikersinfo gefaald. ${error.getDescription()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onSuccess(result: UserProfile) {
                    cachedUserProfile = result

                    binding.lblProfileName.text = result.getUserMetadata()["first_name"] as String?
                    binding.lblProfileFirstName.text =
                        result.getUserMetadata()["last_name"] as String?
                    binding.lblProfileEmail.text = result.email
                    binding.lblProfileTel.text = result.getUserMetadata()["tel"] as String?
                    binding.lblProfileBTW.text = result.getUserMetadata()["btw_nr"] as String?
                }
            })
    }

    private fun logoutWithBrowser() {
        activity?.let {
            WebAuthProvider.logout(account)
                .withScheme(getString(R.string.auth0_scheme))
                .start(
                    it,
                    object : Callback<Void?, AuthenticationException> {
                        override fun onSuccess(result: Void?) {
                            // The user has been logged out!
                            cachedCredentials = null
                            cachedUserProfile = null

                            navigateToLogin()
                        }

                        override fun onFailure(error: AuthenticationException) {
                            Toast.makeText(context, "Logout failed", Toast.LENGTH_LONG).show()
                        }
                    }
                )
        }
    }

    private fun navigateToLogin() {
        val directions = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
        findNavController().navigate(directions)
    }
}
