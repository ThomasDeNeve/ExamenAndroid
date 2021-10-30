package com.example.hier.ui.rooms

import androidx.lifecycle.ViewModel
import com.example.hier.repository.LocationRepository
import com.example.hier.repository.RoomRepository

class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {
    val rooms = locationRepository.getLocations().data?.get(0)?.rooms
}