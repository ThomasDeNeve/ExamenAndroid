package com.example.hier.ui.coworking;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hier.networkModels.CoworkReservationPostModel
import com.example.hier.repository.ReservationRepository
import com.example.hier.util.Resource
import java.text.SimpleDateFormat
import java.util.*

public class CoworkingRecapViewModel(
    private val reservationRepository: ReservationRepository
) : ViewModel() {
    private val _eventSubmit = MutableLiveData<Boolean>()
    val eventSubmit: LiveData<Boolean>
        get() = _eventSubmit

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

    lateinit var response: Resource<String>

    suspend fun onSubmit() {
        val date: Date = Date(_date.value!!)
        val dateString = SimpleDateFormat("dd/MM/yyyy").format(date)
        var user = reservationRepository.getUser()
        val rnw = CoworkReservationPostModel(user.userId, _seatId.value!!, dateString)
        response = reservationRepository.postCoworkReservation(rnw)
        _eventSubmit.value = true
    }

    fun onSubmitComplete() {
        _eventSubmit.value = false
    }
}
