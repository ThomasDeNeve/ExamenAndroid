package com.example.hier.networkModels

import androidx.room.ColumnInfo
import com.example.hier.models.Room
import com.squareup.moshi.Json

// Data class representing the Room objects fetched by the API
data class RoomNetworkModel(
    @Json(name = "id")
    var roomId: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "numberOfSeats")
    var numberOfSeats: Int,
    @ColumnInfo(name = "price")
    var price: Double,
    @ColumnInfo(name = "locationId")
    var locationId: Int
) {
    fun toDataBaseModel(): Room {
        return Room(roomId, name, numberOfSeats, price, locationId)
    }
}