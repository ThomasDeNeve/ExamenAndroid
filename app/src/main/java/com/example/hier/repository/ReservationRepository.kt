package com.example.hier.repository

import com.example.hier.database.LocalDataSource
import com.example.hier.models.Reservation
import com.example.hier.network.RemoteDataSource
import com.example.hier.networkModels.CoworkReservationPostModel
import com.example.hier.util.performGetOperation

class ReservationRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
/*    fun getRooms() = performGetOperation(
        databaseQuery = { localDataSource.getReservations() },
        networkCall = { remoteDataSource.getReservations() },
        saveCallResult = { localDataSource.saveReservations(it.records) }
    )*/

    /**
     *
     *fetch reservations and adapt to Reservation class
     */
    suspend fun getReservations(date: Long): List<Reservation> {
        val response = remoteDataSource.getReservations(date)
        val r = mutableListOf<Reservation>()
        response.data?.forEach { o ->
            r.add(Reservation(from = date, seat = o.seatId.toString()))
        }
        return r
    }

    suspend fun postReservation(coworkReservation: CoworkReservationPostModel){
        val response = remoteDataSource.addReservation(coworkReservation)
    }
}
