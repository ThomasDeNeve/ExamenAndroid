package com.example.hier.repository

import com.example.hier.database.LocalDataSource
import com.example.hier.network.RemoteDataSource
import com.example.hier.networkModels.UserNetworkModel
import com.example.hier.util.Resource

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getUser(username: String) {
        val userNetworkModel: Resource<UserNetworkModel> = remoteDataSource.getUser(username)

        if (userNetworkModel.data != null) {
            localDataSource.saveUser(userNetworkModel.data.toDatabaseModel())
        } else {
            throw IllegalStateException("Failed to fetch user!")
        }
    }
}