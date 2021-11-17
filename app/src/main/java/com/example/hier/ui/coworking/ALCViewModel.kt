package com.example.hier.ui.coworking

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hier.models.DAY
import com.example.hier.models.Reservation
import com.example.hier.repository.ReservationRepository
import java.util.*

class ALCViewModel(val reservationSource: ReservationRepository) : ViewModel(){

    //date as given by user, must be at least today
    var date = System.currentTimeMillis()
    //timespan as given by user; MORNING, AFTERNOON, EVENING or DAY
    var timespan = DAY

    //List of reservations from server for given date and timespan
    val reservationsList : LiveData<List<Reservation>> = reservationSource.getReservations(date)

    //TODO: check availability for every chair.
    var chair1reserved: Boolean =true
    var chair2reserved: Boolean =false
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


}