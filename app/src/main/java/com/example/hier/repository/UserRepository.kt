package com.example.hier.repository

import com.example.hier.database.LocalDataSource
import com.example.hier.network.RemoteDataSource
import com.example.hier.networkModels.UserNetworkModel
import com.example.hier.util.Resource
import com.example.hier.util.performGetOperation

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getUser(username: String) {
        var userNetworkModel: Resource<UserNetworkModel> = remoteDataSource.getUser(username)

        if (userNetworkModel.data != null) {
            userNetworkModel.data?.toDatabaseModel()?.let { localDataSource.saveUser(it) }
        }
    }

    fun getReservations() = performGetOperation(
        databaseQuery = { localDataSource.getReservations() },
        networkCall = { remoteDataSource.getReservations() },
        saveCallResult = { localDataSource.saveReservations(it.records) }
    )
}