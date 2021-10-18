package com.example.hier.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.hier.database.LocalDataSource
import com.example.hier.models.User
import com.example.hier.network.LoginResponse
import com.example.hier.network.RemoteDataSource
import com.example.hier.util.Resource
import com.example.hier.util.Status
import kotlinx.coroutines.Dispatchers

class UserRepository(
    private val remoteDataSource: RemoteDataSource
) {
    fun loginUser(username: String, password: String): LiveData<Resource<LoginResponse>> = liveData(Dispatchers.IO) {
        remoteDataSource.loginUser(username, password)
        /*val temp = remoteDataSource.loginUser(username, password)
        temp.let { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    return a;
                }
            }
        }*/
    }
}