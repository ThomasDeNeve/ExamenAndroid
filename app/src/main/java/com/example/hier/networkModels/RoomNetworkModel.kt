package com.example.hier.networkModels

import com.example.hier.models.Room
import com.squareup.moshi.Json

// Data class representing the Room objects fetched by the API
data class RoomNetworkModel(
    @Json(name = "name")
    val name: String,
//    @Json(name = "description")
//    val description: String
    //TODO add and/or remove fields (this depends on the JSON)
) {
    fun toDataBaseModel(): Room {
        return Room(name)
    }
}