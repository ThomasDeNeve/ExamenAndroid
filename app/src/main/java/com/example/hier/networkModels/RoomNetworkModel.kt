package com.example.hier.networkModels

import androidx.room.ColumnInfo
import com.example.hier.models.Room
import com.squareup.moshi.Json

// Data class representing the Room objects fetched by the API
data class RoomNetworkModel(
    @Json(name = "id")
    var id: Int,
    @Json(name = "name")
    var name: String,
    @Json(name = "numberOfSeats")
    var numberOfSeats: Int,
    @Json(name = "locationId")
    var locationId: Int,
    @Json(name = "priceEvening")
    var priceEvening: Double,
    @Json(name = "priceFullDay")
    var priceFullDay: Double,
    @Json(name = "priceHalfDay")
    var priceHalfDay: Double,
    @Json(name = "priceTwoHours")
    var priceTwoHours: Double
) {
    fun toDataBaseModel(): Room {
        return Room(id, name, numberOfSeats, priceEvening, priceFullDay, priceHalfDay, priceTwoHours, locationId)
    }
}