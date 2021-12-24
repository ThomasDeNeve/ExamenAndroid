package com.example.hier.ui.login

import androidx.lifecycle.ViewModel
import com.example.hier.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var user: String = ""

    suspend fun insertNewUser(user: String?) {
        var userValue: String = ""
        if (user != null) {
            userValue = user
        }
        userRepository.getUser(userValue)
    }
}
