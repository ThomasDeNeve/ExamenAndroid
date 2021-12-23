package com.example.hier.networkModels

import com.example.hier.models.Reservation

data class ReservationNetworkModel(
    var customerId: Int,
    var seat: Int,
    var from: Long,
    //var to: Long,
    var room: String
) {
    fun toDataBaseModel(): Reservation {
        return Reservation(customerId, from,  room, seat.toString())
    }
    // TODO implement this
}