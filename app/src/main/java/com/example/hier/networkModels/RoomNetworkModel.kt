package com.example.hier.networkModels

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.hier.models.Room
import com.squareup.moshi.Json

// Data class representing the Room objects fetched by the API
data class RoomNetworkModel(
    @Json(name = "roomId")
    var roomId: Int = 0,
    @Json(name="name")
    var name: String,
    @Json(name = "priceFullDay")
    var priceFullDay : Double = 0.0,
    @Json(name = "priceHalfDay")
    var priceHalfDay : Double = 0.0,
    @Json(name = "priceEvening")
    var priceEvening : Double = 0.0,
    @Json(name = "priceTwoHours")
    var priceTwoHours : Double = 0.0,
    @Json(name = "numberOfSeats")
    var numberOfSeats : Int = 0
//    @Json(name = "description")
//    val description: String
    //TODO add and/or remove fields (this depends on the JSON)
) {
    fun toDataBaseModel(): Room {
        return Room(roomId,name, priceFullDay, priceHalfDay, priceEvening, priceTwoHours, numberOfSeats)
    }
}