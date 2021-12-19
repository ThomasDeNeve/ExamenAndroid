package com.example.hier.network

import androidx.lifecycle.LiveData
import com.example.hier.models.Room
import com.example.hier.models.User
import com.example.hier.networkModels.RootReservationNetworkModel
import com.example.hier.networkModels.LocationNetworkModel
import com.example.hier.networkModels.MeetingRoomNetworkModel
import com.example.hier.networkModels.UserNetworkModel
import com.example.hier.util.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/customer/GetLoggedIn")
    suspend fun getUser(@Query("username") username: String): Response<UserNetworkModel>

    fun getReservations(): Response<RootReservationNetworkModel> {
        TODO("Not yet implemented")
    }

    @GET("api/Location")
    suspend fun getLocations(): Response<List<LocationNetworkModel>>

    @POST("/api/Reservation/meetingroom")
    suspend fun addReservation(@Body reservation: ReservationPostModel): Response<ReservationPostModel>

    @GET("api/Reservation/availablemeetingrooms")
    suspend fun getAvailableMeetingrooms(@Query("neededseats") neededseats: Int,@Query("locationid") locationid: Int,@Query("datetimeStart") datetimeStart: String,@Query("datetimeEnd") datetimeEnd: String) : Response<List<MeetingRoomNetworkModel>>

    /* suspend fun loginUser(username: String, password: String): Response<LoginResponse> {
         if (username == "admin" && password == "admin") {
             val loginres = LoginResponse(false, "", User());
             Log.e("ApiService", "Got into loginUser method")
             return Response.success(loginres)
         } else {
             return Response.error(1, null)
         }
     }*/
}