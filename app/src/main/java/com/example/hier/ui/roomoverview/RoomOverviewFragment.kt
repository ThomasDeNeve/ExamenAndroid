package com.example.hier.ui.roomoverview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hier.adapters.RoomAdapter
import com.example.hier.databinding.FragmentRoomoverviewBinding
import com.example.hier.models.Room
import com.example.hier.util.Status
import org.koin.android.ext.android.inject

class RoomOverviewFragment : Fragment(), RoomAdapter.RoomClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val overviewViewModel: RoomOverviewViewModel by inject()
        val binding = FragmentRoomoverviewBinding.inflate(inflater, container, false)
        binding.viewModel = overviewViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        // val rooms = viewModel.rooms

        //attaching adapter for recyclerview, pass clicklistener as argument
        val adapter = RoomAdapter(this)
        binding.roomList.adapter = adapter

        //adapter.data=viewModel.rooms

        /*viewModel.rooms.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->  if(resource.status == Status.SUCCESS){
                adapter.data = resource.data!!
            } }
        })*/

        overviewViewModel.rooms.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            adapter.data = resource.data!!
                            //viewModel.setStatus(Status.SUCCESS)
                        }
                        Status.LOADING -> {
                            //viewModel.setStatus(Status.LOADING)
                        }
                        Status.ERROR -> {
                            //viewModel.setStatus(Status.ERROR)
                        }
                    }
                }
            }
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Reserveer zalen"
    }

    override fun onRoomClicked(room: Room) {
        Log.e("test", "clicked on room with roomID ${room.roomId}")

        val directions =
            RoomOverviewFragmentDirections.actionRoomOverviewFragmentToRoomFragment(room.roomId)

        findNavController().navigate(directions)
    }

}