package com.example.hier.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hier.databinding.ListItemRoomBinding
import com.example.hier.models.Room

class RoomAdapter : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    var data = listOf<Room>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = ListItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(view)
    }

    class RoomViewHolder(private val binding : ListItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Room){
            binding.apply {
                room = item
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = data[position]
        holder.bind(room)
    }
}