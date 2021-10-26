package com.example.hier.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class Room(
    @PrimaryKey(autoGenerate = true)
    var roomId: Int = 0,
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name = "priceFullDay")
    var priceFullDay : Double = 0.0,
    @ColumnInfo(name = "priceHalfDay")
    var priceHalfDay : Double = 0.0,
    @ColumnInfo(name = "priceEvening")
    var priceEvening : Double = 0.0,
    @ColumnInfo(name = "priceTwoHours")
    var priceTwoHours : Double = 0.0,
    @ColumnInfo(name = "numberOfSeats")
    var numberOfSeats : Int = 0
    ) {
}