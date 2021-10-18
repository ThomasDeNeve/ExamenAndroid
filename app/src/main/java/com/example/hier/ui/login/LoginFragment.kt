package com.example.hier.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hier.R
import com.example.hier.databinding.FragmentLoginBinding
import com.example.hier.util.Status
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {


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

            var username = binding.editTextTextPersonName.text.toString()
            var password = binding.editTextTextPassword.text.toString()
            //TODO hash password here?

            viewModel.setCredentials(username, password)

            viewModel.loginResponse.observe(viewLifecycleOwner,
                Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            //TODO add logic for logging in
                            Status.SUCCESS -> {
                               Toast.makeText(context, "login successful", Toast.LENGTH_LONG).show()
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
        }

        return binding.root
    }

    private fun navigateToHome(){
        val directions = LoginFragmentDirections.actionLoginFragmentToMainActivity()
        findNavController().navigate(directions)
    }

}