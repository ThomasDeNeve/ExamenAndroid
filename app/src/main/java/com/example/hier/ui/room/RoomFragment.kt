package com.example.hier.ui.room

import android.os.Bundle
import android.util.Log
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
import com.example.hier.util.Status
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
        val binding = FragmentRoomBinding.inflate(inflater, container, false)
        viewModel.setRoom(args.roomId)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.room.observe(
            viewLifecycleOwner,
            { room ->
                roomName = room.name
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
                    )
                    if (viewModel.response.status == Status.SUCCESS) {
                        if (viewModel.response.data.equals("OK"))
                            Toast.makeText(
                                context,
                                "Reservatie werd geregistreerd!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                    } else
                        Toast.makeText(
                            context,
                            "Uw reservatie is helaas mislukt, probeer het later opnieuw",
                            Toast.LENGTH_LONG
                        ).show()
                    onReserveButtonClicked()
                } catch (e: Exception) {
                    Log.e("RoomFragment", e.stackTraceToString())
                    Toast.makeText(
                        container!!.context,
                        "Er ging iets fout bij de reservatie",
                        Toast.LENGTH_LONG
                    ).show()
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
        val directions = RoomFragmentDirections.actionRoomFragmentToChoiceMeetingRoomFragment()
        findNavController().navigate(directions)
    }
}
