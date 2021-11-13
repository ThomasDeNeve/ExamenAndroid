package com.example.hier.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservations")
data class Reservation(var id: Int){
    @PrimaryKey(autoGenerate = true)
    var reservationId = id
}