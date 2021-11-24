package com.example.hier.networkModels

import com.example.hier.models.LocationWithRooms
import com.example.hier.models.Room

data class LocationNetworkModel(
    val id: Int,
    val name: String,
    val street: String,
    val number: Int,
    val postalCode: String,
    val place: String,
    val meetingRooms: List<Room>,
    val coWorkRooms: List<Any>
)