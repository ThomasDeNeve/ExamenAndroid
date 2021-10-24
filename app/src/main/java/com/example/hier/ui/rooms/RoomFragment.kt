package com.example.hier.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hier.R
import com.example.hier.databinding.RoomFragmentBinding
import org.koin.android.ext.android.inject

class RoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RoomViewModel by inject()
        val binding = RoomFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val rooms = viewModel.rooms
        return binding.root
    }

}