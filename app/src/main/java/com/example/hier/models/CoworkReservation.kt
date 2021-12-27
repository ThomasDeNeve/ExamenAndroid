package com.example.hier.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coworkreservation")
data class CoworkReservation(
    @PrimaryKey(autoGenerate = true)
    var reservationId: Int = 0,
    @ColumnInfo(name = "from")
    var from: Long,
    @ColumnInfo(name = "seat")
    var seatId: Int
)
