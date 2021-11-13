package com.example.hier.ui.reservations

import androidx.lifecycle.ViewModel
import com.example.hier.repository.UserRepository

class ReservationsViewModel (private val userRepository: UserRepository) : ViewModel() {
    val reservations = userRepository.getReservations()
}