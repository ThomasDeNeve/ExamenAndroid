package com.example.hier.ui.coworking;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hier.networkModels.CoworkReservationPostModel
import com.example.hier.repository.ReservationRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

public class CoworkingRecapViewModel(
    private val reservationRepository: ReservationRepository
) : ViewModel() {
    private val _eventSubmit = MutableLiveData<Boolean>()
    val eventSubmit: LiveData<Boolean>
        get() = _eventSubmit

    //TODO make suspend & call from lifecyclescope in fragment
    //TODO custId dynamisch instellen!
    fun onSubmit() = runBlocking {
        var date : Date = Date(_date.value!!)
        val dateString = SimpleDateFormat("dd/MM/yyyy").format(date)
        val rnw = CoworkReservationPostModel(1, _seatId.value!!, dateString)
        launch {
            reservationRepository.postCoworkReservation(rnw)
        }
        _eventSubmit.value = true
    }

    fun onSubmitComplete() {
        _eventSubmit.value = false
    }

    private val _location = MutableLiveData<String>()
    val location: LiveData<String>
        get() = _location

    fun setLocation(value: String) {
        _location.value = value
    }

    private val _chamber = MutableLiveData<String>()
    val chamber: LiveData<String>
        get() = _chamber

    fun setChamber(value: String) {
        _chamber.value = value
    }

    private val _seatId = MutableLiveData<Int>()
    val seatId: LiveData<Int>
        get() = _seatId

    fun setSeatId(value: Int) {
        _seatId.value = value
    }

    private val _date = MutableLiveData<Long>()
    val date: LiveData<Long>
        get() = _date

    fun setDate(value: Long) {
        _date.value = value
    }
}
