package com.example.hier.adapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("textFromLong")
fun bindTextFromLong(view: TextView, long: Long){
    val date = Date(long)
    val format = SimpleDateFormat("dd/MM/yyyy")
    val text = format.format(date)
    view.setText(text)
}