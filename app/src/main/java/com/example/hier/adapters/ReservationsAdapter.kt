package com.example.hier.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hier.R
import com.example.hier.models.Reservation

class ReservationsAdapter: ListAdapter<Reservation, ReservationsAdapter.ViewHolder>(ReservationDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.setBackground(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val reservationFrom: TextView = itemView.findViewById(R.id.reservation_item_from)
        private val reservationTo: TextView = itemView.findViewById(R.id.reservation_item_to)
        private val reservationType: TextView = itemView.findViewById(R.id.reservation_item_type)
        private val reservationRoom: TextView = itemView.findViewById(R.id.reservation_item_room)

        private val reservationLayout: LinearLayout = itemView.findViewById(R.id.reservation_item_layout)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.reservation_item_view, parent, false)
                return ViewHolder(view)
            }
        }

        fun setBackground(position: Int) {
            if (position % 2 != 1) {
                reservationLayout.setBackgroundColor(Color.LTGRAY)
            } else {
                reservationLayout.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        fun bind(item: Reservation) {
            reservationFrom.text = item.from
            reservationTo.text = item.to
            reservationType.text = item.roomType
            reservationRoom.text = item.room
        }
    }
}

class ReservationDiffCallback: DiffUtil.ItemCallback<Reservation>() {
    override fun areItemsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
        return oldItem.reservationId == newItem.reservationId
    }

    override fun areContentsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
        return oldItem == newItem
    }

}