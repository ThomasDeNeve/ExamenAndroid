package com.example.hier.networkModels

import com.example.hier.models.Reservation

data class ReservationNetworkModel(var id: Int, var from: Long, var to: Long, var room: String, var seat: String) {
    fun toDataBaseModel(): Reservation {
        return Reservation(id, from, to, room, seat)
    }
    // TODO implement this
}