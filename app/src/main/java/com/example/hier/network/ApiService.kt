package com.example.hier.network

import com.example.hier.networkModels.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/customer/GetLoggedIn")
    suspend fun getUser(@Header("Authorization") token: String, @Query("username") username: String): Response<UserNetworkModel>

    @GET("api/Reservation/availablemeetingrooms")
    suspend fun getAvailableMeetingrooms(
        @Header("Authorization") token: String,
        @Query("neededseats") neededseats: Int,
        @Query("locationid") locationid: Int,
        @Query("datetimeStart") datetimeStart: String,
        @Query("datetimeEnd") datetimeEnd: String
    ): Response<List<MeetingRoomNetworkModel>>

    @GET("api/reservation/coworkroom")
    suspend fun getCoworkReservations(@Header("Authorization") token: String, @Query("date") date: String): Response<List<CoworkReservationReceiveModel>>

    @POST("api/reservation/seat")
    suspend fun postCoworkReservation(@Header("Authorization") token: String, @Body coworkReservation: CoworkReservationPostModel): Response<String>

    @POST("/api/Reservation/meetingroom")
    suspend fun postMeetingroomReservation(@Header("Authorization") token: String, @Body meetingroomReservation: MeetingroomReservationPostModel): Response<String>
}
