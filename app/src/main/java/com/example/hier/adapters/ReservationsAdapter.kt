package com.example.hier.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hier.R
import com.example.hier.models.Reservation

class ReservationsAdapter: RecyclerView.Adapter<TextItemViewHolder>()  {
    var data = listOf<Reservation>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        if (position % 2 == 1) {
            holder.textView.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.textView.setBackgroundColor(Color.TRANSPARENT)
        }
        holder.textView.text = item.room
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.reservation_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }


}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)