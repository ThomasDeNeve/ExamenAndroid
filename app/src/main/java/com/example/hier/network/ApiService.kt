package com.example.hier.network

import com.example.hier.networkModels.RootNetworkModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    //TODO add the URL to the endpoint in the GET (for example: "api/rooms")
    @GET("")
    suspend fun getRooms(): Response<RootNetworkModel>
}