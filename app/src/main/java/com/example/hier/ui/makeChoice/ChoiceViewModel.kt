package com.example.hier.ui.makeChoice

import androidx.lifecycle.ViewModel
import com.example.hier.repository.RoomRepository

class ChoiceViewModel(val repo: RoomRepository) : ViewModel() {
    fun getLocationId(name: String): Int {
        return repo.getLocationIdByName(name)
    }
}
