package com.example.hier.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.hier.R
import com.example.hier.adapters.RoomAdapter
import com.example.hier.databinding.RoomFragmentBinding
import com.example.hier.models.Room
import org.koin.android.ext.android.inject

class RoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: LocationViewModel by inject()
        val binding = RoomFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
       // val rooms = viewModel.rooms

        //attaching adapter for recyclerview
        val adapter = RoomAdapter()
        binding.roomList.adapter = adapter

        adapter.data=viewModel.rooms!!
        return binding.root
    }

}