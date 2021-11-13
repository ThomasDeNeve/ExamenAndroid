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
    var username: String = ""
    var password: String = ""

    fun setCredentials(username: String, password: String){
        //TODO hash password here?
        this.username = username
        this.password = password
        updateLoginUser()


    }


    var responseCount = 0
    val loginResponse : MutableLiveData<Resource<LoginResponse>> by lazy{
        Log.i("LoginViewModel", "Response init: ${responseCount}")
        responseCount += 1
        MutableLiveData<Resource<LoginResponse>>()
    }

    //A common pattern for MutableLiveData adds a private and a public val:
    /*
    private val _loginResponse= MutableLiveData<Resource<LoginResponse>>()
    val loginResponse : LiveData<Resource<LoginResponse>>
        get() = _loginResponse
    */

    private fun updateLoginUser(){
        //loginResponse.value = userRepository.loginUser(username, password).value
        //as a test:
        loginResponse.value = Resource<LoginResponse>(Status.SUCCESS,LoginResponse(true, "testuser", User("admin", "admin")), "true")
        //--> that works

        //addToLoginResponse2()
    }

/*    private fun addToLoginResponse2(){
        var res = LoginResponse(false, "this is a loginresponse", User())
        var resource = Resource(Status.SUCCESS, res, "resource text")
        loginResponse2.value = resource
    }
    var loginResponse2 = MutableLiveData<Resource<LoginResponse>> ()*/

}