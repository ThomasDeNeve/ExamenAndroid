package com.example.hier.repository

import com.example.hier.database.LocalDataSource
import com.example.hier.models.CoworkReservation
import com.example.hier.models.User
import com.example.hier.network.RemoteDataSource
import com.example.hier.networkModels.CoworkReservationPostModel
import com.example.hier.util.Resource
import java.text.SimpleDateFormat
import java.util.*

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
    suspend fun getCoworkReservations(date: Date): List<CoworkReservation> {
        val dateString = SimpleDateFormat("dd/MM/yyyy").format(date)
        val response = remoteDataSource.getReservations(dateString)
        val coworkReservationList = mutableListOf<CoworkReservation>()

        response.data?.forEach { model ->
            coworkReservationList.add(
                CoworkReservation(
                    from = date.time,
                    seatId = model.seatId
                )
            )
        }
        return coworkReservationList
    }

    suspend fun postCoworkReservation(coworkReservation: CoworkReservationPostModel): Resource<String> {
        return remoteDataSource.addCoworkReservation(coworkReservation)
    }


    fun getUser(): User {
        return localDataSource.getCurrentUser()
    }
}
