package com.example.hier.ui.reservations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.MeetingroomReservation
import com.example.hier.models.Reservation
import com.example.hier.repository.ReservationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ReservationsViewModel(private val reservationRepository: ReservationRepository) : ViewModel() {
    private val _response = MutableLiveData<List<Reservation>>()

    val response: MutableLiveData<List<Reservation>>
        get() = _response

    //val reservations = populateData()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        populateData()
    }

    private fun populateData() {
        var reservations = mutableListOf<Reservation>()
        coroutineScope.launch {
            reservations = reservationRepository.getAllReservations(0) as MutableList<Reservation>
            _response.value = reservations
            Log.i("FirstReservation", response.value?.get(0)?.to.toString().take(10))
        }
    }
}
