package com.example.hier.ui.room

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.hier.databinding.FragmentRoomBinding
import org.koin.android.ext.android.inject

class RoomFragment : Fragment() {
    private val args: RoomFragmentArgs by navArgs()
    private var roomName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RoomViewModel by inject()
        val binding = FragmentRoomBinding.inflate(inflater, container, false)
        Log.e("roomfragment", "room id passed by args is ${args.roomId}")
        viewModel.setRoom(args.roomId)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.room.observe(viewLifecycleOwner, Observer { room ->
            roomName = room.name
           // viewModel.initializeLocation(room.locationId)
        })

        /*viewModel.location.observe(viewLifecycleOwner, Observer { loc ->

        })*/


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = roomName
        //TODO find out why this doesn't work
    }
}