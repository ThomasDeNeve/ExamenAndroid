package com.example.hier.ui.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hier.database.RoomDao
import com.example.hier.models.Location
import com.example.hier.models.Room
import com.example.hier.repository.RoomRepository

class RoomViewModel(private val roomRepository: RoomDao, application: Application) : AndroidViewModel(application) {
    private lateinit var _room: LiveData<Room>
    val room: LiveData<Room>
        get() = _room

    private lateinit var _location: LiveData<Location>
    val location: LiveData<Location>
        get() = _location

    fun setRoom(roomId: Int) {
        _room = roomRepository.getRoom(roomId)

        //Log.e("roomviewmodel", "databasequery returned ${_room.value?.name}")
    }

    fun initializeLocation(locationId: Int) {
        _location = roomRepository.getLocation(locationId)

    }
}