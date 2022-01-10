package com.example.hier.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hier.R
import com.example.hier.models.Reservation

class ReservationsAdapter: RecyclerView.Adapter<ReservationsAdapter.ViewHolder>()  {
    var data = listOf<Reservation>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        if (position % 2 != 1) {
            holder.reservationLayout.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.reservationLayout.setBackgroundColor(Color.TRANSPARENT)
        }
        holder.reservationFrom.text = item.from
        holder.reservationTo.text = item.to
        holder.reservationType.text = item.roomType
        holder.reservationRoom.text = item.room
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.reservation_item_view, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val reservationFrom: TextView = itemView.findViewById(R.id.reservation_item_from)
        val reservationTo: TextView = itemView.findViewById(R.id.reservation_item_to)
        val reservationType: TextView = itemView.findViewById(R.id.reservation_item_type)
        val reservationRoom: TextView = itemView.findViewById(R.id.reservation_item_room)

        val reservationLayout: LinearLayout = itemView.findViewById(R.id.reservation_item_layout)
    }
}