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

    suspend fun getReservations(date: Long): List<Reservation> {
        val response = remoteDataSource.getReservations(date)
        val r = mutableListOf<Reservation>()
        response.data?.forEach { o ->
            r.add(Reservation(from = date, seat = o.seat))
        }
        return r
    }
}
