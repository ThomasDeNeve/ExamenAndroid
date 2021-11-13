package com.example.hier.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.hier.database.LocalDataSource
import com.example.hier.models.User
import com.example.hier.network.LoginResponse
import com.example.hier.network.RemoteDataSource
import com.example.hier.util.Resource
import com.example.hier.util.Status
import com.example.hier.util.performGetOperation
import kotlinx.coroutines.Dispatchers

class UserRepository(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) {
    fun loginUser(username: String, password: String) = performGetOperation(
        databaseQuery = { localDataSource.getUser(username)},
        networkCall = { remoteDataSource.loginUser(username, password)},
        saveCallResult = { localDataSource.saveUser(it)}
    )

    fun getReservations() = performGetOperation(
        databaseQuery = { localDataSource.getReservations() },
        networkCall = { remoteDataSource.getReservations() },
        saveCallResult = { localDataSource.saveReservations(it.records) }
    )

    fun getUser() = NotImplementedError("not yet implemented") //TODO implement this


    /*fun loginUser(username: String, password: String): LiveData<Resource<LoginResponse>> = liveData(Dispatchers.IO) {
        Log.e("UserRepository", "got into loginUser")
        emit(remoteDataSource.loginUser(username, password))
        /*val temp = remoteDataSource.loginUser(username, password)
        temp.let { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    return a;
                }
            }
        }*/
    }*/
}