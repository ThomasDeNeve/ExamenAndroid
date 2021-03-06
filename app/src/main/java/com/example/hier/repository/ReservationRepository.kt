package com.example.hier.repository

import android.util.Log
import com.example.hier.MyApplication.Companion.cachedUserProfile
import com.example.hier.database.LocalDataSource
import com.example.hier.models.CoworkReservation
import com.example.hier.models.Reservation
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
    /**
     *
     *fetch reservations and adapt to Reservation class
     */
    suspend fun getCoworkReservations(date: Date): List<CoworkReservation> {
        val dateString = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
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

    suspend fun getAllReservations(roomType: Int): List<Reservation> {
        val user: User = if (cachedUserProfile != null) {
            val username: String = cachedUserProfile?.name.toString()
            localDataSource.getUser(username)
        } else {
            Log.e("ReservationRepository", "CachedUserProfile was null!")
            localDataSource.getNewestUser()
        }

        return remoteDataSource.getAllReservations(user.username, roomType).data!!
    }

    suspend fun postCoworkReservation(coworkReservation: CoworkReservationPostModel): Resource<String> {
        return remoteDataSource.addCoworkReservation(coworkReservation)
    }

    suspend fun getUser(): User {
        if (cachedUserProfile != null) {
            val username: String = cachedUserProfile?.name.toString()
            return localDataSource.getUser(username)
        } else Log.e("ReservationRepository", "CachedUserProfile was null!")
        // return localDataSource.getUser(username).value!!
        return localDataSource.getNewestUser()
    }
}
