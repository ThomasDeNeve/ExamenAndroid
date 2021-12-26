package com.example.hier.ui.login

import androidx.lifecycle.ViewModel
import com.example.hier.models.User
import com.example.hier.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var user: String = ""

    suspend fun insertNewUser(username: String?) {
        /*var user: User = */userRepository.getUser(username.toString())

    }
}
