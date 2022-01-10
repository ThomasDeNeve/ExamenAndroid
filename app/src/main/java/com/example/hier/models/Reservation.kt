package com.example.hier.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservation")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    var reservationId: Int = 0,
    @ColumnInfo(name = "customer")
    var customer: String,
    @ColumnInfo(name = "from")
    var from: String,
    @ColumnInfo(name = "to")
    var to: String,
    @ColumnInfo(name = "roomType")
    var roomType: String,
    @ColumnInfo(name = "room")
    var room: String
)
