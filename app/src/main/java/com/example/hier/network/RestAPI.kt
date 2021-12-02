package com.example.hier.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi
{
    @Headers("Content-Type: application/json")
    @POST("/api/Reservation/meetingroom")
    fun addReservation(@Body reservation: ReservationPostModel): Call<ReservationPostModel>
}