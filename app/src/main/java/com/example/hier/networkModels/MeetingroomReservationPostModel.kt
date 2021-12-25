package com.example.hier.networkModels

import com.google.gson.annotations.SerializedName

data class MeetingroomReservationPostModel(
    @SerializedName("roomId") val roomId: Int,
    @SerializedName("customerId") val customerId: Int,
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: String,
    @SerializedName("timeslot") val timeslot: String
)
