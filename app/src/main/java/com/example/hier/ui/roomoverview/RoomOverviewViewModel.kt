package com.example.hier.ui.roomoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hier.repository.RoomRepository

class RoomOverviewViewModel(private val roomRepository: RoomRepository) : ViewModel()
{
    var rooms = roomRepository.getRooms()

    fun getAvailableRooms(locationId:Int, numberOfSeats:Int, date:String)
    {
        rooms = roomRepository.getAvailableRooms(locationId, numberOfSeats,date)
    }
}
