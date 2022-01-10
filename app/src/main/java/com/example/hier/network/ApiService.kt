package com.example.hier.network

import com.example.hier.models.Reservation
import com.example.hier.networkModels.CoworkReservationPostModel
import com.example.hier.networkModels.CoworkReservationReceiveModel
import com.example.hier.networkModels.MeetingRoomNetworkModel
import com.example.hier.networkModels.MeetingroomReservationPostModel
import com.example.hier.networkModels.UserNetworkModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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

    @GET("api/reservation/allreservations")
    suspend fun getAllReservations(@Header("Authorization") token: String, @Query("username") customer: String, @Query("roomType") roomType: Int): Response<List<Reservation>>
}
