package com.example.hier.networkModels

import com.example.hier.models.Room

data class MeetingRoomNetworkModel(
    val id: Int,
    val name: String,
    val numberOfSeats: Int,
    val locationId: Int,
    val priceEvening: Double,
    val priceFullDay: Double,
    val priceHalfDay: Double,
    val priceTwoHours: Double
) {
    fun toDatabaseModel(): Room {
        return Room(
            id,
            name,
            numberOfSeats,
            priceEvening,
            priceFullDay,
            priceHalfDay,
            priceTwoHours,
            locationId
        )
    }
}
