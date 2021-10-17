package com.example.hier.network

class RemoteDataSource(val apiService: ApiService) : BaseDataSource() {
    suspend fun getRooms() = getResult { apiService.getRooms() }
}