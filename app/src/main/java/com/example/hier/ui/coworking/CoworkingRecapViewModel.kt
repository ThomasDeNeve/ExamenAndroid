package com.example.hier.ui.coworking;

import android.text.style.TtsSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hier.repository.ReservationRepository;
import java.util.*

public class CoworkingRecapViewModel(
    val reservationSource: ReservationRepository
) : ViewModel() {
    private val _eventSubmit = MutableLiveData<Boolean>()
    val eventSubmit: LiveData<Boolean>
        get() = _eventSubmit

    fun onSubmit() {
        _eventSubmit.value = true
    }

    fun onSubmitComplete() {
        _eventSubmit.value = false
    }

    var location: String = ""
    var chamber: String = ""
    var seatId: Int = 0
    var date: Long = 0
    var timeslot: String = ""
}
