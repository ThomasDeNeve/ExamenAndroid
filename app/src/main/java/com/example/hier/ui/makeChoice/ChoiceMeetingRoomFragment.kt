package com.example.hier.ui.makeChoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hier.databinding.FragmentChoiceBinding

class ChoiceMeetingRoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChoiceBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnLocOne.setOnClickListener {
            val directions =
                ChoiceMeetingRoomFragmentDirections.actionChoiceMeetingRoomFragmentToRoomOverviewFragment(
                    1
                )
            findNavController().navigate(directions)
        }

        binding.btnLocTwo.setOnClickListener {
            val directions =
                ChoiceMeetingRoomFragmentDirections.actionChoiceMeetingRoomFragmentToRoomOverviewFragment(
                    2
                )
            findNavController().navigate(directions)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Kies locatie voor zalen"
    }
}
