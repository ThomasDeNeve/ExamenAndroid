package com.example.hier.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meetingroomreservation")
data class MeetingroomReservation(
    @PrimaryKey(autoGenerate = true)
    var reservationId: Int = 0,
    @ColumnInfo(name = "from")
    var from: Long,
    @ColumnInfo(name = "to")
    var to: Long,
    @ColumnInfo(name = "room")
    var room: String,
    /*@ColumnInfo(name = "seat")
    var seat: String*/
)