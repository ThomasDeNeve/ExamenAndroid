package com.example.hier.adapters

import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import java.util.*

@BindingAdapter("android:date")
fun setDate(view: CalendarView, date: Long) {
    if (view.date != date) {
        view.date = date
    }
}

/*@BindingAdapter("app:minDate")
fun setMinDate(view: CalendarView, date: Long) {
    if (view.minDate != date) {
        view.minDate = date
    }
}*/

@BindingAdapter(
    value = ["android:onSelectedDayChange", "android:dateAttrChanged"],
    requireAll = false
)
fun setListeners(
    view: CalendarView, onDayChange: OnDateChangeListener?,
    attrChange: InverseBindingListener?
) {
    if (attrChange == null) {
        view.setOnDateChangeListener(onDayChange)
    } else {
        view.setOnDateChangeListener { view, year, month, dayOfMonth ->
            onDayChange?.onSelectedDayChange(view, year, month, dayOfMonth)
            val instance: Calendar = Calendar.getInstance()
            instance.set(year, month, dayOfMonth)
            view.date = instance.timeInMillis
            attrChange.onChange()
        }
    }
}