package com.example.hier.ui.reservations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hier.R
import com.example.hier.databinding.ReservationsFragmentBinding
import com.example.hier.databinding.RoomFragmentBinding
import com.example.hier.ui.rooms.RoomViewModel
import org.koin.android.ext.android.inject

class ReservationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: ReservationsViewModel by inject()
        val binding = ReservationsFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val reservations = viewModel.reservations
        return binding.root
    }

}