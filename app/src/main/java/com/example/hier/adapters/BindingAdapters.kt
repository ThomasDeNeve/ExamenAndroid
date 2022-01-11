package com.example.hier.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("textFromLong")
fun bindTextFromLong(view: TextView, long: Long) {
    val date = Date(long)
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val text = format.format(date)
    view.text = text
}

@BindingAdapter("bindRoomImage")
fun bindRoomImage(statusImageView: ImageView, imageName: String?) {
    if (imageName != null) {
        val fullName = "@drawable/" + imageName.removeSuffix(".jpeg")
        val context = statusImageView.context
        val resID: Int = context.resources.getIdentifier(fullName, "drawable", context.packageName)
        statusImageView.setImageResource(resID)
    }
}
