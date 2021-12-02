package com.example.hier.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService
{
    fun addReservation(reservation: ReservationPostModel, onResult: (ReservationPostModel?) -> Unit)
    {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addReservation(reservation).enqueue(object : Callback<ReservationPostModel>
        {
                override fun onFailure(call: Call<ReservationPostModel>, t: Throwable)
                {
                    t.message?.let { Log.e("ERROR", it) }
                    onResult(null)
                }
                override fun onResponse( call: Call<ReservationPostModel>, response: Response<ReservationPostModel>)
                {
                    Log.i("SUCCESS", "")
                    val addedReservation = response.body()
                    onResult(addedReservation)
                }
        })
    }
}