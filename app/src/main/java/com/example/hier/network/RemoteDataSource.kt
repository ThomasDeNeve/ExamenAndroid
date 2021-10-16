package com.example.hier.network

class RemoteDataSource(val apiService: apiService) : BaseDataSource() {
    suspend fun getRooms() = getResult { apiService.getRooms() }
}