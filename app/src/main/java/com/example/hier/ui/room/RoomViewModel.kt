package com.example.hier.ui.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.hier.database.RoomDao
import com.example.hier.models.Location
import com.example.hier.models.Room

class RoomViewModel(private val roomRepository: RoomDao, application: Application) :
    AndroidViewModel(application) {
    private lateinit var _room: LiveData<Room>
    val room: LiveData<Room>
        get() = _room

    private lateinit var _location: LiveData<Location>
    val location: LiveData<Location>
        get() = _location

    fun setRoom(roomId: Int) {
        _room = roomRepository.getRoom(roomId)
    }
}