package com.example.hier.models

import androidx.room.Entity
import androidx.room.PrimaryKey

const val MORNING = 0
const val AFTERNOON = 1
const val EVENING = 2
const val DAY = 3

@Entity(tableName = "reservations")
data class Reservation(var id: Int, var seat: Int){
    @PrimaryKey(autoGenerate = true)
    var reservationId = id

    var seatId = seat
}