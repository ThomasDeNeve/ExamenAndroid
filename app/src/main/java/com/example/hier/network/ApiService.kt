package com.example.hier.network

import android.util.Log
import com.example.hier.models.User
import com.example.hier.networkModels.RootNetworkModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    //TODO add the URL to the endpoint in the GET (for example: "api/rooms")
    @GET("api/rooms")
    suspend fun getRooms(): Response<RootNetworkModel>

    //TODO add URL (api/login)
    @FormUrlEncoded
    @POST("api/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>

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