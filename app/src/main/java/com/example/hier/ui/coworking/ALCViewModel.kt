package com.example.hier.ui.coworking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.hier.models.CoworkReservation
import com.example.hier.repository.ReservationRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

class ALCViewModel(
    val reservationRepository: ReservationRepository
) : ViewModel() {

    //date as given by user, must be at least today
    var _date = MutableLiveData<Long>()
    val date: LiveData<Long>
        get() = _date

    private val _chamber = MutableLiveData<String>()
    val chamber: LiveData<String>
        get() = _chamber

    //List of reservations from server for given date and timespan
    var reservationsList = MutableLiveData<List<CoworkReservation>>()

    //eventflag
    private val _eventChairClicked = MutableLiveData<Boolean>()
    val eventChairClicked: LiveData<Boolean>
        get() = _eventChairClicked

    private val _clickedSeatId = MutableLiveData<Int>()
    val clickedSeatId: LiveData<Int>
        get() = _clickedSeatId

    private val _chair1reserved = MutableLiveData<Boolean>()
    val chair1reserved: LiveData<Boolean>
        get() = _chair1reserved
    private val _chair2reserved = MutableLiveData<Boolean>()
    val chair2reserved: LiveData<Boolean>
        get() = _chair2reserved
    private val _chair3reserved = MutableLiveData<Boolean>()
    val chair3reserved: LiveData<Boolean>
        get() = _chair3reserved
    private val _chair4reserved = MutableLiveData<Boolean>()
    val chair4reserved: LiveData<Boolean>
        get() = _chair4reserved
    private val _chair5reserved = MutableLiveData<Boolean>()
    val chair5reserved: LiveData<Boolean>
        get() = _chair5reserved
    private val _chair6reserved = MutableLiveData<Boolean>()
    val chair6reserved: LiveData<Boolean>
        get() = _chair6reserved
    private val _chair7reserved = MutableLiveData<Boolean>()
    val chair7reserved: LiveData<Boolean>
        get() = _chair7reserved
    private val _chair8reserved = MutableLiveData<Boolean>()
    val chair8reserved: LiveData<Boolean>
        get() = _chair8reserved
    private val _chair9reserved = MutableLiveData<Boolean>()
    val chair9reserved: LiveData<Boolean>
        get() = _chair9reserved
    private val _chair10reserved = MutableLiveData<Boolean>()
    val chair10reserved: LiveData<Boolean>
        get() = _chair10reserved
    private val _chair11reserved = MutableLiveData<Boolean>()
    val chair11reserved: LiveData<Boolean>
        get() = _chair11reserved
    private val _chair12reserved = MutableLiveData<Boolean>()
    val chair12reserved: LiveData<Boolean>
        get() = _chair12reserved
    private val _chair13reserved = MutableLiveData<Boolean>()
    val chair13reserved: LiveData<Boolean>
        get() = _chair13reserved
    private val _chair14reserved = MutableLiveData<Boolean>()
    val chair14reserved: LiveData<Boolean>
        get() = _chair14reserved
    private val _chair15reserved = MutableLiveData<Boolean>()
    val chair15reserved: LiveData<Boolean>
        get() = _chair15reserved
    private val _chair16reserved = MutableLiveData<Boolean>()
    val chair16reserved: LiveData<Boolean>
        get() = _chair16reserved

    init {
        var today = System.currentTimeMillis()
        today = today.div(86400000L) //remove hours and minutes from date
        today = today.times(86400000L)
        _date.value = today
        _date.observeForever(Observer { newDate ->
            checkAvailability(newDate)
        })

    }

    fun checkAvailability(newDate: Long) = runBlocking {
        val dateAsDate : Date = Date(newDate)

        _chair1reserved.value = false
        _chair2reserved.value = false
        _chair3reserved.value = false
        _chair4reserved.value = false
        _chair5reserved.value = false
        _chair6reserved.value = false
        _chair7reserved.value = false
        _chair8reserved.value = false
        _chair9reserved.value = false
        _chair10reserved.value = false
        _chair11reserved.value = false
        _chair12reserved.value = false
        _chair13reserved.value = false
        _chair14reserved.value = false
        _chair15reserved.value = false
        _chair16reserved.value = false
        launch {
            reservationsList.postValue(reservationRepository.getCoworkReservations(dateAsDate))
            reservationsList.value?.let {
                for (x in reservationsList.value!!) {
                    if (x.seatId == 1)
                        _chair1reserved.value = true
                    if (x.seatId == 2)
                        _chair2reserved.value = true
                    if (x.seatId == 3)
                        _chair3reserved.value = true
                    if (x.seatId == 4)
                        _chair4reserved.value = true
                    if (x.seatId == 5)
                        _chair5reserved.value = true
                    if (x.seatId == 6)
                        _chair6reserved.value = true
                    if (x.seatId == 7)
                        _chair7reserved.value = true
                    if (x.seatId == 8)
                        _chair8reserved.value = true
                    if (x.seatId == 9)
                        _chair9reserved.value = true
                    if (x.seatId == 10)
                        _chair10reserved.value = true
                    if (x.seatId == 11)
                        _chair11reserved.value = true
                    if (x.seatId == 12)
                        _chair12reserved.value = true
                    if (x.seatId == 13)
                        _chair13reserved.value = true
                    if (x.seatId == 14)
                        _chair14reserved.value = true
                    if (x.seatId == 15)
                        _chair15reserved.value = true
                    if (x.seatId == 16)
                        _chair16reserved.value = true
                }
            }
        }
    }

    /**
     * Clickhandler for chair
     */
    fun onGreenChairClicked(value: Int) {
        _clickedSeatId.value = value
        if (value in 1..6)
            _chamber.value = "hier.beneden"
        if (value > 6)
            _chamber.value = "hier.boven"
        _eventChairClicked.value = true
    }

    /**
     * handler for event completion on clicked chair
     */
    fun onGreenChairClickedComplete() {
        _eventChairClicked.value = false
    }
}