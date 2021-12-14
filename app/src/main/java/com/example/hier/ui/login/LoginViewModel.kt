package com.example.hier.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.User
import com.example.hier.network.LoginResponse
import com.example.hier.repository.UserRepository
import com.example.hier.util.Resource
import com.example.hier.util.Status

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var user: String = ""

    fun insertNewUser(user: String?) {
        var userValue: String = ""
        if (user != null)
        {
            userValue = user
        }
        val userObject = userRepository.getUser(userValue)
    }
}