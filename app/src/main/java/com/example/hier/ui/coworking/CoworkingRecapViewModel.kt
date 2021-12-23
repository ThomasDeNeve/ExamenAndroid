package com.example.hier.ui.coworking;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hier.networkModels.ReservationNetworkModel
import com.example.hier.repository.ReservationRepository;
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

public class CoworkingRecapViewModel(
    val reservationSource: ReservationRepository
) : ViewModel() {
    private val _eventSubmit = MutableLiveData<Boolean>()
    val eventSubmit: LiveData<Boolean>
        get() = _eventSubmit

    fun onSubmit() = runBlocking {
        val rnw = ReservationNetworkModel(0, _seatId.value!!,_date.value!!, _chamber.value!!)
        launch {
            reservationSource.postReservation(rnw)
        }
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
