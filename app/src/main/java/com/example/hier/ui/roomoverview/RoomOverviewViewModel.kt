package com.example.hier.ui.roomoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hier.repository.RoomRepository

class RoomOverviewViewModel(private val roomRepository: RoomRepository) : ViewModel()
{
    var rooms = roomRepository.getRooms()
    //var rooms = roomRepository.getAvailableRooms(8, 1,"0001-01-01 00:00:00.000000")

    fun getAvailableRooms(neededseats:Int, locationid:Int, date:String)
    {
        rooms = roomRepository.getAvailableRooms(neededseats, locationid,date)
    }
}