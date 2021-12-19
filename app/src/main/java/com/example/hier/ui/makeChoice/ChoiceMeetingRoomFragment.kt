package com.example.hier.ui.makeChoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hier.databinding.FragmentChoiceBinding
import org.koin.android.ext.android.inject

class ChoiceMeetingRoomFragment : Fragment() {
//    private val args: RoomOverviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ChoiceViewModel by inject()
        val binding = FragmentChoiceBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnLocOne.setOnClickListener {
            // val locationId: Int = viewModel.getLocationId("HIER")
            val directions = ChoiceMeetingRoomFragmentDirections.actionChoiceMeetingRoomFragmentToRoomOverviewFragment(1)
            findNavController().navigate(directions)
        }

        binding.btnLocTwo.setOnClickListener {
            // val locationId: Int = viewModel.getLocationId("Kluizen")
            val directions = ChoiceMeetingRoomFragmentDirections.actionChoiceMeetingRoomFragmentToRoomOverviewFragment(2)
            findNavController().navigate(directions)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Kies locatie voor zalen"
    }
}
