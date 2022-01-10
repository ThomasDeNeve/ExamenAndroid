package com.example.hier.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hier.R
import com.example.hier.databinding.ReservationItemViewBinding
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

    class ViewHolder private constructor(val binding: ReservationItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        private val reservationFrom: TextView = binding.reservationItemFrom
        private val reservationTo: TextView = binding.reservationItemTo
        private val reservationType: TextView = binding.reservationItemType
        private val reservationRoom: TextView = binding.reservationItemRoom

        private val reservationLayout: LinearLayout = binding.reservationItemLayout

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ReservationItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
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
            binding.reservation = item
            binding.executePendingBindings()
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