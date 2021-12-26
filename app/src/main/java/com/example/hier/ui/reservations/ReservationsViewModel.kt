package com.example.hier.ui.reservations

import androidx.lifecycle.ViewModel
import com.example.hier.models.Reservation
import com.example.hier.repository.UserRepository
import java.util.*

class ReservationsViewModel(private val userRepository: UserRepository) : ViewModel() {
//    val reservations = userRepository.getReservations()
    val reservations = populateData()

    private fun populateData(): List<Reservation> {
        val reservations = mutableListOf<Reservation>()
        for (i in 1..20) {
            val from = GregorianCalendar(2021, 12, i).timeInMillis
            val to = GregorianCalendar(2021, 12, i).timeInMillis
            val res = Reservation(i, from, to, "/", (0..10).random().toString())

            reservations.add(res)
        }
        for (i in 1..12) {
            val from = GregorianCalendar(2021, i, 5).timeInMillis
            val to = GregorianCalendar(2021, i, 6).timeInMillis
            val res = Reservation(i, from, to, "HIER.ginder", "/")

            reservations.add(res)
        }
        return reservations
    }
}