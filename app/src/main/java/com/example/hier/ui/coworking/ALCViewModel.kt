package com.example.hier.ui.coworking

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.DAY
import com.example.hier.models.Reservation
import com.example.hier.repository.ReservationRepository
import java.util.*

class ALCViewModel(
    val reservationSource: ReservationRepository
    ) : ViewModel(){

    //date as given by user, must be at least today
    private val _date = MutableLiveData<Long>()
    val date: LiveData<Long>
        get() = _date

    private val _chamber = MutableLiveData<String>()
    val chamber: LiveData<String>
        get() = _chamber

    //List of reservations from server for given date and timespan
    //var reservationsList : LiveData<List<Reservation>> = reservationSource.getReservations(date)

    private val _eventChairClicked = MutableLiveData<Boolean>()
    val eventChairClicked: LiveData<Boolean>
        get() = _eventChairClicked

    private val _clickedSeatId = MutableLiveData<Int>()
    val clickedSeatId: LiveData<Int>
        get() = _clickedSeatId

    //TODO: check availability for every chair.
    val chair1reserved: Boolean =true
    val chair2reserved: Boolean =false
    var chair3reserved: Boolean =true
    var chair4reserved: Boolean =false
    var chair5reserved: Boolean =true
    var chair6reserved: Boolean =false
    var chair7reserved: Boolean =true
    var chair8reserved: Boolean =false
    var chair9reserved: Boolean =true
    var chair10reserved: Boolean =false
    var chair11reserved: Boolean =true
    var chair12reserved: Boolean =false
    var chair13reserved: Boolean =true
    var chair14reserved: Boolean =false
    var chair15reserved: Boolean =true
    var chair16reserved: Boolean =false

    init {
        _date.value = System.currentTimeMillis()
    }

    fun onGreenChairClicked(value: Int){
        _clickedSeatId.value = value
        if(0<value && value<7)
            _chamber.value = "hier.beneden"
        if(value>6)
            _chamber.value = "hier.boven"
        _eventChairClicked.value = true
    }

    fun onGreenChairClickedComplete(){
        _eventChairClicked.value = false
    }
}