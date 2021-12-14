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
        //TODO: Submit to backend
        _eventSubmit.value = true
    }

    fun onSubmitComplete() {
        _eventSubmit.value = false
    }

    private val _location = MutableLiveData<String>()
    val location: LiveData<String>
        get() = _location

    fun setLocation(value: String){
        _location.value = value
    }

    private val _chamber = MutableLiveData<String>()
    val chamber: LiveData<String>
        get() = _chamber
    fun setChamber(value: String){
        _chamber.value = value
    }

    private val _seatId = MutableLiveData<Int>()
    val seatId: LiveData<Int>
        get() = _seatId
    fun setSeatId(value: Int){
        _seatId.value = value
    }

    private val _date = MutableLiveData<Long>()
    val date: LiveData<Long>
        get() = _date
    fun setDate(value: Long){
        _date.value = value
    }
}
