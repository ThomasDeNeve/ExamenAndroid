package com.example.hier.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hier.databinding.FragmentRoomBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class RoomFragment : Fragment() {
    private val args: RoomFragmentArgs by navArgs()
    private var roomName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: RoomViewModel by inject()
        // val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        // val dataSource = ApplicationDatabase.getDatabase(application).roomDao()
        // val viewModelFactory = RoomViewModelFactory(dataSource, application)

        // val viewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)

        val binding = FragmentRoomBinding.inflate(inflater, container, false)
        // Log.e("roomfragment", "room id passed by args is ${args.roomId}")
        viewModel.setRoom(args.roomId)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.room.observe(
            viewLifecycleOwner,
            { room ->
                roomName = room.name
                // viewModel.initializeLocation(room.locationId)
            }
        )

        binding.btnReserve.setOnClickListener {
            lifecycleScope.launch {
                try {
                    viewModel.addReservation(
                        args.roomId,
                        args.user,
                        args.dateStart,
                        args.dateEnd,
                        args.timeslot
                    ) // TODO add actual customer ID and catch exception when duplicate
                    onReserveButtonClicked()
                    Toast.makeText(container!!.context, "Reservatie werd geregistreerd!", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(container!!.context, "Er ging iets fout bij de reservatie", Toast.LENGTH_LONG).show()
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = roomName
    }

    fun onReserveButtonClicked() {
        val directions = RoomFragmentDirections.actionRoomFragmentToChoiceCoworkingFragment()

        findNavController().navigate(directions)
    }
}
