package com.example.hier.networkModels

import com.example.hier.models.Reservation

data class ReservationNetworkModel(var id: Int) {
    fun toDataBaseModel(): Reservation {
        return Reservation(id)
    }
    // TODO implement this
}