package com.example.hier.network
import com.example.hier.MyApplication
import com.example.hier.MyApplication.Companion.apiAccessToken

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getUser(username: String) = getResult { apiService.getUser("Bearer " + apiAccessToken, username) }

    suspend fun getReservations() = getResult { apiService.getReservations("Bearer " + apiAccessToken) }

    suspend fun getLocations() = getResult { apiService.getLocations("Bearer " + apiAccessToken) }

    suspend fun getAvailableMeetingrooms(neededseats: Int, locationid: Int, datetimeStart: String, datetimeEnd: String) = getResult { apiService.getAvailableMeetingrooms("Bearer " + apiAccessToken, neededseats, locationid, datetimeStart, datetimeEnd) }

    suspend fun addReservation(reservationPostModel: ReservationPostModel) = getResult { apiService.addReservation("Bearer " + apiAccessToken, reservationPostModel) }

}
