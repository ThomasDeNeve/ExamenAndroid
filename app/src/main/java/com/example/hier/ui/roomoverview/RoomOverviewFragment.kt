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
import androidx.navigation.fragment.navArgs
import com.example.hier.adapters.RoomAdapter
import com.example.hier.databinding.FragmentRoomoverviewBinding
import com.example.hier.models.Room
import com.example.hier.util.Status
import org.koin.android.ext.android.inject
import java.util.*

import android.widget.*

class RoomOverviewFragment : Fragment(), RoomAdapter.RoomClickListener
{
    private val args: RoomOverviewFragmentArgs by navArgs()
    private val overviewViewModel: RoomOverviewViewModel by inject()

    //private var locationId:Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val binding = FragmentRoomoverviewBinding.inflate(inflater, container, false)

        buildDatePicker(binding)
        buildAmountOfSeatsSpinner(binding)
        buildTimeOfDaySpinner(binding)
        buildRoomOverview(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Reserveer zalen"

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

    private fun buildRoomOverview(binding: FragmentRoomoverviewBinding)
    {
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
                    }
                    Status.LOADING ->
                    {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR ->
                    {
                        Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show()
                    }
                }
                }
            })
    }

    private fun buildAmountOfSeatsSpinner(binding: FragmentRoomoverviewBinding)
    {
        val spinner = binding.seatSpinner
        val items = arrayOf(8,14,40,50,120) //TODO: get capacity list from db

        spinner.adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, items)

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?)
            {
                overviewViewModel.neededseats=8

                //Retreive and update the available rooms list
                overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long )
            {
                val item = parent?.getItemAtPosition(position) as Int
                overviewViewModel.neededseats = item

                //Retreive and update the available rooms list
                overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
            }
        }
    }

    private fun buildTimeOfDaySpinner(binding: FragmentRoomoverviewBinding)
    {
        val spinner = binding.timeSpinner
        val items = arrayOf("Voormiddag", "Namiddag", "Hele dag", "Avond") //TODO: 2 hours slot edge case (needs lots of refactoring)

        spinner.adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, items)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?)
            {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long )
            {
                when (parent?.getItemAtPosition(position).toString())
                {
                    "Voormiddag" -> rebuildDate("08:00:00" , "12:00:00")
                    "Namiddag" -> rebuildDate("13:00:00", "17:00:00")
                    "Hele dag" -> rebuildDate("08:00:00", "17:00:00")
                    "Avond" -> rebuildDate("17:00:00", "21:00:00")
                }
            }
        }
    }

    private fun rebuildDate(timeStart: String, timeEnd: String)
    {
        var date = overviewViewModel.datetimeStart

        //remove the time part of the datetime
        date = date.split(" ")[0]

        //add the new start time to the dates
        val startDateWithTime = "$date $timeStart"
        val endDateWithTime = "$date $timeEnd"

        overviewViewModel.datetimeStart = startDateWithTime
        overviewViewModel.datetimeEnd = endDateWithTime
        overviewViewModel.getAvavailableRooms()

    }
    private fun buildDatePicker(binding: FragmentRoomoverviewBinding)
    {
        val now = Calendar.getInstance()

        binding.datePicker.init(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
        {
                view, year, month, day -> val month = month + 1

            overviewViewModel.datetimeStart = "$year-$month-$day 00:00:01"
            overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
            Log.d("date", day.toString() + month.toString() + year.toString())
        }
    }
}