package com.example.hier.ui.roomoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.Room
import com.example.hier.models.User
import com.example.hier.repository.RoomRepository
import com.example.hier.util.Resource
import kotlinx.coroutines.DelicateCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

class RoomOverviewViewModel(private val roomRepository: RoomRepository) : ViewModel() {
    var neededseats: Int = 8 // init on minimum amount of needed seats
    var location: Int = 0 // location is passed by RoomOverviewFragment on init using args.locationId
    var datetimeStart: String = getStartDateTime() // initialize date on current datetime
    var datetimeEnd: String = getEndDateTime()
    var currentUser : User = roomRepository.getCurrentUser()

    @DelicateCoroutinesApi
    var rooms = getAvavailableRooms()

    // update the list of rooms using minimum amount of needed seats, location id, and requested date
    @DelicateCoroutinesApi
    fun getAvavailableRooms(): LiveData<Resource<List<Room>>> {
        return roomRepository.getRooms(neededseats, location, datetimeStart, datetimeEnd)
    }

    // Get the actual date and return as String
    private fun getStartDateTime(): String {
        // Get the current date
        val currentdate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        var cdatestring = currentdate.format(Date()).toString()
        cdatestring = cdatestring.split(" ")[0]
        cdatestring += " 08:00:00"
        // format to string
        return cdatestring // currentdate.format(Date())
    }

    private fun getEndDateTime(): String {
        val currentdate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        var cdatestring = currentdate.format(Date()).toString()
        cdatestring = cdatestring.split(" ")[0]
        cdatestring += " 12:00:00"
        // format to string
        return cdatestring // currentdate.format(Date())
    }
}
