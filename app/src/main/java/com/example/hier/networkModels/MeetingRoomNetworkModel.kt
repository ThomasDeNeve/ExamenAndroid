package com.example.hier.networkModels

import com.example.hier.models.Location
import com.example.hier.models.Room

data class MeetingRoomNetworkModel(
    val id: Int,
    val name: String,
    val numberOfSeats: Int,
    val locationId: Int,
    val priceEvening: Int,
    val priceFullDay: Int,
    val priceHalfDay: Int,
    val priceTwoHours: Int
)