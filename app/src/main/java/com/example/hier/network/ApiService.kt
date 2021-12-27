package com.example.hier.network

import com.example.hier.networkModels.MeetingRoomNetworkModel
import com.example.hier.networkModels.RootReservationNetworkModel
import com.example.hier.networkModels.UserNetworkModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("api/customer/GetLoggedIn")
    suspend fun getUser(@Query("username") username: String): Response<UserNetworkModel>

    fun getReservations(): Response<RootReservationNetworkModel> {
        TODO("Not yet implemented")
    }

    @POST("/api/Reservation/meetingroom")
    suspend fun addReservation(@Body reservation: ReservationPostModel): Response<String>

    @GET("api/Reservation/availablemeetingrooms")
    suspend fun getAvailableMeetingrooms(
        @Query("neededseats") neededseats: Int,
        @Query("locationid") locationid: Int,
        @Query("datetimeStart") datetimeStart: String,
        @Query("datetimeEnd") datetimeEnd: String
    ): Response<List<MeetingRoomNetworkModel>>
}
