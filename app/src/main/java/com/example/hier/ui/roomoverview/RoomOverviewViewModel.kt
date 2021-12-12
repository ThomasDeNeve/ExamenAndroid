package com.example.hier.ui.roomoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.Room
import com.example.hier.repository.RoomRepository
import com.example.hier.util.Resource
import java.text.SimpleDateFormat
import java.util.*

class RoomOverviewViewModel(private val roomRepository: RoomRepository) : ViewModel()
{
    var neededseats:Int=0 //init on minimum amount of needed seats
    var location:Int=0 //location is passed by RoomOverviewFragment on init using args.locationId
    var date:String = getCurrentDate() //initialize date on current datetime

    var rooms = getAvavailableRooms()

    //update the list of rooms using minimum amount of needed seats, location id, and requested date
    fun getAvavailableRooms() : LiveData<Resource<List<Room>>>
    {
        return roomRepository.getRooms(neededseats, location, date)
    }

    //Get the actual date and return as String
    fun getCurrentDate(): String
    {
        //Get the current date
        val currentdate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        //format to string
        val dateString = currentdate.format(Date())
        return dateString
    }
}