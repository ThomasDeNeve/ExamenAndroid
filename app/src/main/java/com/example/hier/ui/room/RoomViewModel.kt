package com.example.hier.ui.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.Location
import com.example.hier.models.Room
import com.example.hier.networkModels.MeetingroomReservationPostModel
import com.example.hier.repository.RoomRepository

class RoomViewModel(private val roomRepository: RoomRepository) : ViewModel() {
    private lateinit var _room: LiveData<Room>
    val room: LiveData<Room>
        get() = _room

    private lateinit var _location: LiveData<Location>

    val location: LiveData<Location>
        get() = _location

    fun setRoom(roomId: Int) {
        _room = roomRepository.getRoomById(roomId)
    }

    suspend fun addReservation(
        roomId: Int,
        customerId: Int,
        from: String,
        to: String,
        timeslot: String
    ) {
        val reservationPostModel = MeetingroomReservationPostModel(roomId, customerId, from, to, timeslot)
        roomRepository.addReservation(reservationPostModel)
    }
}
