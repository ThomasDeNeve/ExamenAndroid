package com.example.hier.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class Room(
    @PrimaryKey
    var roomId: Int,
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name = "numberOfSeats")
    var numberOfSeats: Int,
    @ColumnInfo(name="price")
    var price: Double,
    @ColumnInfo(name="locationId")
    var locationId: Int
    ) {
}