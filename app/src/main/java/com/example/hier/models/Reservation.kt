package com.example.hier.models

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservations")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    var reservationId: Int = 0,
    @ColumnInfo(name = "from")
    @Nullable
    var from: Long = 0L,
    //@ColumnInfo(name = "to")
    //@Nullable
    //var to: Long = 0L,
    @ColumnInfo(name = "room")
    @Nullable
    var room: String = "",
    @ColumnInfo(name = "seat")
    @Nullable
    var seat: String = ""
) {
}