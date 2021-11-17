package com.example.hier.networkModels

import com.example.hier.models.Reservation

data class ReservationNetworkModel(var id: Int, var seatId: Int) {
    fun toDataBaseModel(): Reservation {
        return Reservation(id, seatId)
    }
    // TODO implement this
}