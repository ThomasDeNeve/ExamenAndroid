package com.example.hier.ui.rooms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.Room
import com.example.hier.repository.RoomRepository

class RoomViewModel(private val roomRepository: RoomRepository) : ViewModel() {
    val rooms = roomRepository.getRooms()
}