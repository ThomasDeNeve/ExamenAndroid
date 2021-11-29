package com.example.hier.ui.roomoverview

import androidx.lifecycle.ViewModel
import com.example.hier.repository.RoomRepository

class RoomOverviewViewModel(private val roomRepository: RoomRepository) : ViewModel() {
    val rooms = roomRepository.getRooms()
}
