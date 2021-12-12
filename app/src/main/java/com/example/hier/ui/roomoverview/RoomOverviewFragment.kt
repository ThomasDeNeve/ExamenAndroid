package com.example.hier.ui.roomoverview

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hier.adapters.RoomAdapter
import com.example.hier.databinding.FragmentRoomoverviewBinding
import com.example.hier.models.Room
import com.example.hier.ui.makeChoice.ChoiceMeetingRoomFragmentDirections
import com.example.hier.util.Status
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class RoomOverviewFragment : Fragment(), RoomAdapter.RoomClickListener
{
    private val args: RoomOverviewFragmentArgs by navArgs()
    private val overviewViewModel: RoomOverviewViewModel by inject()

    private var locationId:Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val binding = FragmentRoomoverviewBinding.inflate(inflater, container, false)

        setActionListeners(binding)

        //locationId = args.locationId

        binding.viewModel = overviewViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //attaching adapter for recyclerview, pass clicklistener as argument
        val adapter = RoomAdapter(this)
        binding.roomList.adapter = adapter

        overviewViewModel.rooms.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { resource -> when (resource.status)
                {
                        Status.SUCCESS ->
                        {
                            adapter.data = resource.data!!
                            //viewModel.setStatus(Status.SUCCESS)
                        }
                        Status.LOADING ->
                        {
                            //viewModel.setStatus(Status.LOADING)
                        }
                        Status.ERROR ->
                        {
                            Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show()
                            //viewModel.setStatus(Status.ERROR)
                        }
                }
                }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Reserveer zalen"

        overviewViewModel.neededseats=14
        overviewViewModel.location = args.locationId
        //the current date is initialized in RoomOverviewFragment, this does not need to change on initializing this fragment

        overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
    }

    override fun onRoomClicked(room: Room)
    {
        //Log.e("test", "clicked on room with roomID ${room.roomId}")

        val directions =
            RoomOverviewFragmentDirections.actionRoomOverviewFragmentToRoomFragment(room.id)

        findNavController().navigate(directions)
    }

    fun setActionListeners(binding: FragmentRoomoverviewBinding)
    {
        val now = Calendar.getInstance()

        binding.datePicker.init(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
        {
                view, year, month, day -> val month = month + 1
            overviewViewModel.date = "$year-$month-$day 00:00:00"
            overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
            Log.d("date", day.toString() + month.toString() + year.toString())
        }

        //binding.datePicker.minDate = System.currentTimeMillis() - 1000



        /*binding.btnLocOne.setOnClickListener {
            //val locationId: Int = viewModel.getLocationId("HIER")
            val directions = ChoiceMeetingRoomFragmentDirections.actionChoiceMeetingRoomFragmentToRoomOverviewFragment(1)
            findNavController().navigate(directions)
        }*/
    }
}