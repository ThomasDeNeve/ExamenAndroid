package com.example.hier.ui.roomoverview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hier.R
import com.example.hier.adapters.RoomAdapter
import com.example.hier.databinding.FragmentRoomoverviewBinding
import com.example.hier.models.Room
import com.example.hier.util.Status
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*

@DelicateCoroutinesApi
class RoomOverviewFragment : Fragment(), RoomAdapter.RoomClickListener {
    private val args: RoomOverviewFragmentArgs by navArgs()
    private val overviewViewModel: RoomOverviewViewModel by inject()

    private var timeslot: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRoomoverviewBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            overviewViewModel.initializeUser()
        }

        buildDatePicker(binding)
        buildAmountOfSeatsSpinner(binding)
        buildTimeOfDaySpinner(binding)
        buildRoomOverview(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Reserveer zalen"

        overviewViewModel.location = args.locationId
        // the current date is initialized in RoomOverviewFragment, this does not need to change on initializing this fragment

        overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
    }

    override fun onRoomClicked(room: Room) {
        val user = overviewViewModel.currentUser.userId
        val directions =
            RoomOverviewFragmentDirections.actionRoomOverviewFragmentToRoomFragment(
                room.id,
                overviewViewModel.datetimeStart,
                overviewViewModel.datetimeEnd,
                user,
                timeslot
            )

        findNavController().navigate(directions)
    }

    private fun buildRoomOverview(binding: FragmentRoomoverviewBinding) {
        binding.viewModel = overviewViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // attaching adapter for recyclerview, pass clicklistener as argument
        val adapter = RoomAdapter(this)
        binding.roomList.adapter = adapter

        overviewViewModel.rooms.observe(
            viewLifecycleOwner,
            {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            adapter.data = resource.data!!
                        }
                        Status.LOADING -> {
                            Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        )
    }

    private fun buildAmountOfSeatsSpinner(binding: FragmentRoomoverviewBinding) {
        val spinner = binding.seatSpinner
        val items = arrayOf(8, 14, 40, 50, 120) // TODO: get capacity list from db

        spinner.adapter = ArrayAdapter(this.requireContext(), R.layout.custom_spinner_list, items)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                overviewViewModel.neededseats = 8

                // Retreive and update the available rooms list
                overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = parent?.getItemAtPosition(position) as Int
                overviewViewModel.neededseats = item

                // Retreive and update the available rooms list
                overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
            }
        }
    }

    private fun buildTimeOfDaySpinner(binding: FragmentRoomoverviewBinding) {
        val spinner = binding.timeSpinner
        val items = arrayOf(
            "Voormiddag",
            "Namiddag",
            "Hele dag",
            "Avond"
        ) // TODO: 2 hours slot edge case (needs refactoring)

        spinner.adapter = ArrayAdapter(this.requireContext(), R.layout.custom_spinner_list, items)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                rebuildDateAndOverviewViewModel("08:00:00", "12:00:00")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (parent?.getItemAtPosition(position).toString()) {
                    "Voormiddag" -> {
                        rebuildDateAndOverviewViewModel("08:00:00", "12:00:00")
                        timeslot = "Voormiddag"
                    }
                    "Namiddag" -> {
                        rebuildDateAndOverviewViewModel("13:00:00", "17:00:00")
                        timeslot = "Namiddag"
                    }
                    "Hele dag" -> {
                        rebuildDateAndOverviewViewModel("08:00:00", "17:00:00")
                        timeslot = "Volledige dag"
                    } // show enkel avond, extra api calls (gelijkaardig voor 2 uur?)
                    "Avond" -> {
                        rebuildDateAndOverviewViewModel("17:00:00", "21:00:00")
                        timeslot = "Avond"
                    }
                }
            }
        }
    }

    private fun rebuildDateAndOverviewViewModel(timeStart: String, timeEnd: String) {
        var date = overviewViewModel.datetimeStart

        // remove the time part of the datetime
        date = date.split(" ")[0]

        // add the new start time to the dates
        val startDateWithTime = "$date $timeStart"
        val endDateWithTime = "$date $timeEnd"

        overviewViewModel.datetimeStart = startDateWithTime
        overviewViewModel.datetimeEnd = endDateWithTime
        overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
    }

    private fun buildDatePicker(binding: FragmentRoomoverviewBinding) {
        val now = Calendar.getInstance()

        binding.datePicker.init(
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        ) { _, year, month, day ->
            val month = month + 1

            overviewViewModel.datetimeStart = "$year-$month-$day 08:00:00"
            overviewViewModel.datetimeEnd = "$year-$month-$day 12:00:00"
            overviewViewModel.rooms = overviewViewModel.getAvavailableRooms()
            Log.d("date", day.toString() + month.toString() + year.toString())
        }
    }
}
