package com.example.hier.network

import com.example.hier.networkModels.CoworkReservationPostModel

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource()
{
    suspend fun getUser(username: String) = getResult { apiService.getUser(username) }

    //suspend fun getReservations() = getResult { apiService.getReservations() }

    suspend fun getReservations(date: String) = getResult { apiService.getReservations(date) }

    suspend fun getLocations() = getResult { apiService.getLocations() }

    suspend fun getAvailableMeetingrooms(neededseats: Int, locationid: Int, datetimeStart: String, datetimeEnd: String) = getResult { apiService.getAvailableMeetingrooms(neededseats, locationid, datetimeStart, datetimeEnd) }

    suspend fun addCoworkReservation(coworkReservationPostModel: CoworkReservationPostModel) = getResult { apiService.postCoworkReservation(coworkReservationPostModel) }

    suspend fun addMeetingroomReservation(reservationPostModel: ReservationPostModel) = getResult { apiService.addReservation(reservationPostModel) }
}
