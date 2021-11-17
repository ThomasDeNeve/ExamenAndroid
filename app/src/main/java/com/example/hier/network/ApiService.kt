package com.example.hier.network

import com.example.hier.networkModels.RootReservationNetworkModel
import com.example.hier.networkModels.RootRoomNetworkModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

interface ApiService {
    //TODO add the URL to the endpoint in the GET (for example: "api/rooms")
    @GET("api/recipes")
    suspend fun getRooms(): Response<RootRoomNetworkModel>

    //TODO add URL (api/login)
    @FormUrlEncoded
    @POST("api/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    fun getReservations() : Response<RootReservationNetworkModel>{
        TODO("Not yet implemented")
    }

    fun getReservations(date: Date) : Response<RootReservationNetworkModel>{
        TODO("Not yet implemented")
    }

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