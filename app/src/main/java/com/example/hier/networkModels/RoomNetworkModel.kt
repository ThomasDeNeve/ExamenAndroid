package com.example.hier.networkModels

import androidx.room.ColumnInfo
import com.example.hier.models.Room
import com.squareup.moshi.Json

// Data class representing the Room objects fetched by the API
data class RoomNetworkModel(
    @Json(name = "id")
    var roomId: Int,
    @Json(name = "name")
    var name: String,
    @Json(name = "numberOfSeats")
    var numberOfSeats: Int,
    @Json(name = "priceEvening")
    var priceEvening: Double,
    @Json(name = "priceFullDay")
    var priceFullDay: Double,
    @Json(name = "priceHalfDay")
    var priceHalfDay: Double,
    @Json(name = "priceTwoHours")
    var priceTwoHours: Double,
    @Json(name = "locationId")
    var locationId: Int
) {
    fun toDataBaseModel(): Room {
        return Room(roomId, name, numberOfSeats, priceEvening, priceFullDay, priceHalfDay, priceTwoHours, locationId)
    }
}