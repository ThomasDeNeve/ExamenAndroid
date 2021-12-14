package com.example.hier.network

import com.google.gson.annotations.SerializedName

data class MeetingroomsGetModel (
    @SerializedName("locationId") val locationId: Int,
    @SerializedName("numberOfSeats") val numberOfSeats: Int,
    @SerializedName("date") val date:String
)