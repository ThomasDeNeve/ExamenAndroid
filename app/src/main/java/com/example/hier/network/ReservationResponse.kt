package com.example.hier.network

import com.example.hier.models.Reservation

data class ReservationResponse(val error: Boolean, val message: String, val reservation: Reservation)