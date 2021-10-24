package com.example.hier.repository

import com.example.hier.database.LocalDataSource
import com.example.hier.network.RemoteDataSource
import com.example.hier.util.performGetOperation

class ReservationRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getRooms() = performGetOperation(
        databaseQuery = { localDataSource.getReservations() },
        networkCall = { remoteDataSource.getReservations() },
        saveCallResult = { localDataSource.saveReservations(it.records) }
    )
}