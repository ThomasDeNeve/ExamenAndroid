package com.example.hier.network

import android.util.Log
import com.example.hier.models.User
import com.example.hier.util.Resource
import retrofit2.Response

class RemoteDataSource(val apiService: ApiService) : BaseDataSource() {
    suspend fun getRooms() = getResult { apiService.getRooms() }
    suspend fun loginUser(username: String, password: String) = getResult { apiService.loginUser(username, password) }
    suspend fun getReservations() = getResult { apiService.getReservations() }

    /*suspend fun loginUser (username: String, password: String) : Resource<LoginResponse>{
        Log.e("RemoteDataSource", "got into loginUser")
        if (username == "admin" && password == "admin") {
            val loginres = LoginResponse(false, "", User());
            Log.e("RemoteDataSource", "Got into loginUser method")
            return Resource.success(loginres)
        } else {
            return Resource.error("fail")
        }
    }*/
}