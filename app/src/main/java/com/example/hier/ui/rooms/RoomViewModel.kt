package com.example.hier.ui.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hier.repository.RoomRepository

class RoomViewModel(private val roomRepository: RoomRepository) : ViewModel() {
    val rooms = roomRepository.getRooms()
}