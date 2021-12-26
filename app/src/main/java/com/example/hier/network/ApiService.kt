package com.example.hier.network

import com.example.hier.networkModels.LocationNetworkModel
import com.example.hier.networkModels.MeetingRoomNetworkModel
import com.example.hier.networkModels.RootReservationNetworkModel
import com.example.hier.networkModels.UserNetworkModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/customer/GetLoggedIn")
    suspend fun getUser(@Header("Authorization") token: String, @Query("username") username: String): Response<UserNetworkModel>

    fun getReservations(@Header("Authorization") token: String): Response<RootReservationNetworkModel> {
        TODO("Not yet implemented")
    }

    @GET("api/Location")
    suspend fun getLocations(@Header("Authorization") token: String): Response<List<LocationNetworkModel>>

    @POST("/api/Reservation/meetingroom")
    suspend fun addReservation(@Header("Authorization") token: String, @Body reservation: ReservationPostModel): Response<String>

    @GET("api/Reservation/availablemeetingrooms")
    suspend fun getAvailableMeetingrooms(@Header("Authorization") token: String, @Query("neededseats") neededseats: Int, @Query("locationid") locationid: Int, @Query("datetimeStart") datetimeStart: String, @Query("datetimeEnd") datetimeEnd: String): Response<List<MeetingRoomNetworkModel>>

}
