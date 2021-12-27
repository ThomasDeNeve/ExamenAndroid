package com.example.hier.ui.coworking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.CoworkReservation
import com.example.hier.repository.ReservationRepository
import java.util.*

class CoworkingViewModel(
    private val reservationRepository: ReservationRepository
) : ViewModel() {

    //date as given by user, must be at least today
    var dateMutable = MutableLiveData<Long>()
    val date: LiveData<Long>
        get() = dateMutable

    private val _chamber = MutableLiveData<String>()
    val chamber: LiveData<String>
        get() = _chamber

    //List of reservations from server for given date and timespan
    private var reservationsList = MutableLiveData<List<CoworkReservation>>()

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

    val listOfChairs: List<LiveData<Boolean>> = listOf(
        chair1reserved,
        chair2reserved,
        chair3reserved,
        chair4reserved,
        chair5reserved,
        chair6reserved,
        chair7reserved,
        chair8reserved,
        chair9reserved,
        chair10reserved,
        chair11reserved,
        chair12reserved,
        chair13reserved
    )

    fun setInitialDate() {
        var today = System.currentTimeMillis()
        today = today.div(86400000L) //remove hours and minutes from date
        today = today.times(86400000L)
        dateMutable.value = today
    }

    suspend fun checkAvailability(newDate: Long) {
        val dateAsDate = Date(newDate)

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

        reservationsList.value = reservationRepository.getCoworkReservations(dateAsDate)
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
            }
        }
    }

    /**
     * Clickhandler for chair
     */
    fun onGreenChairClicked(value: Int) {
        _clickedSeatId.value = value
        when (value) {
            in 1..4 -> _chamber.value = "Bureel 1 - gelijkvloers"
            in 5..7 -> _chamber.value = "Bureel 2 - gelijkvloers"
            else -> _chamber.value = "Coworking 1 - gelijkvloers"
        }
        _eventChairClicked.value = true
    }

    /**
     * handler for event completion on clicked chair
     */
    fun onGreenChairClickedComplete() {
        _eventChairClicked.value = false
    }
}