package com.example.hier.network

import com.example.hier.networkModels.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("api/customer/GetLoggedIn")
    suspend fun getUser(@Header("Authorization") token: String, @Query("username") username: String): Response<UserNetworkModel>

    @GET("api/Reservation/availablemeetingrooms")
    suspend fun getAvailableMeetingrooms(
        @Query("neededseats") neededseats: Int,
        @Query("locationid") locationid: Int,
        @Query("datetimeStart") datetimeStart: String,
        @Query("datetimeEnd") datetimeEnd: String
    ): Response<List<MeetingRoomNetworkModel>>

    @GET("api/reservation/coworkroom")
    suspend fun getCoworkReservations(@Query("date") date: String): Response<List<CoworkReservationReceiveModel>>

    @POST("api/reservation/seat")
    suspend fun postCoworkReservation(@Body coworkReservation: CoworkReservationPostModel): Response<String>

    @POST("/api/Reservation/meetingroom")
    suspend fun postMeetingroomReservation(@Body meetingroomReservation: MeetingroomReservationPostModel): Response<String>
}
