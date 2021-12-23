package com.example.hier.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hier.databinding.ListItemRoomBinding
import com.example.hier.models.Room

class RoomAdapter(private val roomClickListener: RoomClickListener) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    var data = listOf<Room>()
        set(value) {
            val valueSorted = value.sortedWith { lhs, rhs ->
                // sort rooms by location | -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                if (lhs.locationId < rhs.locationId) -1 else if (lhs.locationId > rhs.locationId) 1 else 0
            }
            field = valueSorted
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = ListItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = data[position]
        holder.bind(room, roomClickListener)
    }

    class RoomViewHolder(private val binding: ListItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Room, listener: RoomClickListener) {
            itemView.setOnClickListener {
                listener.onRoomClicked(item)
            }
            binding.apply {
                room = item
                drawable = Drawable.createFromPath(item.imageName)
                executePendingBindings()
            }
        }
    }

    interface RoomClickListener {
        fun onRoomClicked(room: Room)
    }
}
