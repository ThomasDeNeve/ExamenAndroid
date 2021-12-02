package com.example.hier.network

import com.example.hier.networkModels.RootReservationNetworkModel
import com.example.hier.networkModels.LocationNetworkModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService
{
    //TODO add URL (api/login)
    @FormUrlEncoded
    @POST("api/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    fun getReservations(): Response<RootReservationNetworkModel> {
        TODO("Not yet implemented")
    }

    @GET("api/Location")
    suspend fun getLocations(): Response<List<LocationNetworkModel>>

    @POST("/api/Reservation/meetingroom")
    suspend fun addReservation(@Body reservation: ReservationPostModel): Response<ReservationPostModel>

    /*@FormUrlEncoded
    @POST("api/reservation/meetingroom")
    suspend fun reserveRoom(
        @Field("RoomId") roomId: Int,
        @Field("CustomerId") customerId: Int
    ): Response<ReservationResponse>*/


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