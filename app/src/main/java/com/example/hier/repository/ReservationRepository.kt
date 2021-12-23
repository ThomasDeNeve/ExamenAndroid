package com.example.hier.repository

import com.example.hier.database.LocalDataSource
import com.example.hier.models.Reservation
import com.example.hier.network.RemoteDataSource
import com.example.hier.networkModels.ReservationNetworkModel
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

    /**
     *
     *fetch reservations and adapt to Reservation class
     */
    suspend fun getReservations(date: Long): List<Reservation> {
        val response = remoteDataSource.getReservations(date)
        val r = mutableListOf<Reservation>()
        response.data?.forEach { o ->
            r.add(Reservation(from = date, seat = o.seat.toString()))
        }
        return r
    }

    suspend fun postReservation(reservation: ReservationNetworkModel){
        val response = remoteDataSource.addReservation(reservation)
    }
}
