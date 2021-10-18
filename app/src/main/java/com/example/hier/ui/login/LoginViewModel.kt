package com.example.hier.ui.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.User
import com.example.hier.network.LoginResponse
import com.example.hier.repository.UserRepository
import com.example.hier.util.Resource
import com.example.hier.util.Status

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var username: String = ""
    var password: String = ""

    fun setCredentials(username: String, password: String){
        this.username = username
        this.password = password
        initializeLoginResponse()
    }

    lateinit var loginResponse : LiveData<Resource<LoginResponse>>
    private fun initializeLoginResponse(){
        loginResponse = userRepository.loginUser(username, password)
    }

}