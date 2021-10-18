package com.example.hier.network

class RemoteDataSource(val apiService: ApiService) : BaseDataSource() {
    suspend fun getRooms() = getResult { apiService.getRooms() }
    suspend fun loginUser(username: String, password: String) = getResult { apiService.loginUser(username, password) }
}