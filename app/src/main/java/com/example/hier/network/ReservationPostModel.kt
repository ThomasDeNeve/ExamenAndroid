package com.example.hier.network

import com.google.gson.annotations.SerializedName

data class ReservationPostModel (
    @SerializedName("roomId") val roomId: Int?,
    @SerializedName("customerId") val customerId: Int?
)
