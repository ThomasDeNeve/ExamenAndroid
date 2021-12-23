package com.example.hier.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("bindRoomImage")
fun bindRoomImage(statusImageView: ImageView, imageName: String) {
    val fullName = "@drawable/" + imageName.removeSuffix(".jpeg")
    val context = statusImageView.context
    val resID: Int = context.resources.getIdentifier(fullName, "drawable", context.packageName)
    statusImageView.setImageResource(resID)
}

