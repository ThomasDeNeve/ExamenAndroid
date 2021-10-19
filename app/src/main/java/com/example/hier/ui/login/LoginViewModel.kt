package com.example.hier.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
        updateLoginUser()
    }

    val loginResponse : MutableLiveData<Resource<LoginResponse>> by lazy{
        MutableLiveData<Resource<LoginResponse>>()
    }
    private fun updateLoginUser(){
        loginResponse.value = userRepository.loginUser(username, password).value
        //addToLoginResponse2()
    }

/*    private fun addToLoginResponse2(){
        var res = LoginResponse(false, "this is a loginresponse", User())
        var resource = Resource(Status.SUCCESS, res, "resource text")
        loginResponse2.value = resource
    }
    var loginResponse2 = MutableLiveData<Resource<LoginResponse>> ()*/

}