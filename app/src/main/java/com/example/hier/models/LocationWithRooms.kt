package com.example.hier.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

//@Entity(tableName = "locationWithRooms")
data class LocationWithRooms(
    @Embedded
    var location: Location,
    @Relation(parentColumn = "id", entityColumn = "locationId", entity = Room::class)
    var rooms: List<Room>
) {

}