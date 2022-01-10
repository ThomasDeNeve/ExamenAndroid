package com.example.hier.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.hier.models.Reservation

@BindingAdapter("reservationFromFormatted")
fun TextView.setReservationFromFormatted(item: Reservation?) {
    item?.let {
        text = item.from
    }
}

@BindingAdapter("reservationToFormatted")
fun TextView.setReservationToFormatted(item: Reservation?) {
    item?.let {
        text = item.to
    }
}

@BindingAdapter("reservationTypeFormatted")
fun TextView.setReservationTypeFormatted(item: Reservation?) {
    item?.let {
        text = item.roomType
    }
}

@BindingAdapter("reservationRoomFormatted")
fun TextView.setReservationRoomFormatted(item: Reservation?) {
    item?.let {
        text = item.room
    }
}