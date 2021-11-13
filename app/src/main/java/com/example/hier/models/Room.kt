package com.example.hier.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class Room(
    @PrimaryKey
    var roomId: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "numberOfSeats")
    var numberOfSeats: Int,
    @ColumnInfo(name = "priceEvening")
    var priceEvening: Double,
    @ColumnInfo(name = "priceFullDay")
    var priceFullDay: Double,
    @ColumnInfo(name = "priceHalfDay")
    var priceHalfDay: Double,
    @ColumnInfo(name = "priceTwoHours")
    var priceTwoHours: Double,
    @ColumnInfo(name = "locationId")
    var locationId: Int
) {
}