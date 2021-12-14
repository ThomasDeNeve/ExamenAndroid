package com.example.hier.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.hier.database.ApplicationDatabase
import com.example.hier.databinding.FragmentRoomBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class RoomFragment : Fragment() {
    private val args: RoomFragmentArgs by navArgs()
    private var roomName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RoomViewModel by inject()
        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = ApplicationDatabase.getDatabase(application).roomDao()
        //val viewModelFactory = RoomViewModelFactory(dataSource, application)

        //val viewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)


        val binding = FragmentRoomBinding.inflate(inflater, container, false)
        //Log.e("roomfragment", "room id passed by args is ${args.roomId}")
        viewModel.setRoom(args.roomId)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.room.observe(viewLifecycleOwner, Observer { room ->
            roomName = room.name
            // viewModel.initializeLocation(room.locationId)
        })

        binding.btnReserve.setOnClickListener {
            lifecycleScope.launch {
                viewModel.addReservation(args.roomId, 1) //TODO add actual customer ID
            }

            //val directions = RoomFragmentDirections.actionRoomFragmentToReservationsFragment(roomId) //.actionChoiceMeetingRoomFragmentToRoomOverviewFragment(roomId)
            //findNavController().navigate(directions)

            //viewModel.saveReservation(roomId, 1)


            //System.out.println(response.body().string());

            /*val apiService = RestApiService()
            val reservation = ReservationPostModel(
                roomId = id,
                customerId = 1
            )

            apiService.addReservation(reservation)
            {
                if (it?.roomId != null)
                {
                    //it = newly added user parsed as response
                    // it?.id = newly added user ID
                }
            }
        }*/


            /*viewModel.location.observe(viewLifecycleOwner, Observer { loc ->

        })*/

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = roomName
        //TODO find out why this doesn't work
    }
}