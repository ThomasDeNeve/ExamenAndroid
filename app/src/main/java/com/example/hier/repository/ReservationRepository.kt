package com.example.hier.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hier.database.LocalDataSource
import com.example.hier.models.Reservation
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

    fun getReservations(date: Long) : LiveData<List<Reservation>> {
        //TODO: Implement function
        return MutableLiveData<List<Reservation>>()
    }
}
