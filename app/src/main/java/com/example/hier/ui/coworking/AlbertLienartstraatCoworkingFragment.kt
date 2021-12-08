package com.example.hier.ui.coworking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hier.R
import com.example.hier.databinding.FragmentAlbertLienartstraatCoworkingBinding
import org.koin.android.ext.android.inject

class AlbertLienartstraatCoworkingFragment : Fragment() {
    private lateinit var binding: FragmentAlbertLienartstraatCoworkingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbertLienartstraatCoworkingBinding.inflate(inflater, container, false)
        val viewmodel: ALCViewModel by inject()
        binding.viewModel= viewmodel

        ArrayAdapter.createFromResource(this.context!!, R.array.tijdssloten, android.R.layout.simple_spinner_item).also {
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.tijdsslotSpinner.adapter= adapter
        }
        /*viewmodel.eventChairClicked.observe(this, Observer{ chairClick ->
            if(chairClick){

            }

        })*/

        binding.chair1.setOnClickListener{chairClicked(1)}
        binding.chair2.setOnClickListener{chairClicked(2)}
        binding.chair3.setOnClickListener{chairClicked(3)}
        binding.chair4.setOnClickListener{chairClicked(4)}
        binding.chair5.setOnClickListener{chairClicked(5)}
        binding.chair6.setOnClickListener{chairClicked(6)}
        binding.chair7.setOnClickListener{chairClicked(7)}
        binding.chair8.setOnClickListener{chairClicked(8)}
        binding.chair9.setOnClickListener{chairClicked(9)}
        binding.chair10.setOnClickListener{chairClicked(10)}
        binding.chair11.setOnClickListener{chairClicked(11)}
        binding.chair12.setOnClickListener{chairClicked(12)}
        binding.chair13.setOnClickListener{chairClicked(13)}
        binding.chair14.setOnClickListener{chairClicked(14)}
        binding.chair15.setOnClickListener{chairClicked(15)}
        binding.chair16.setOnClickListener{chairClicked(16)}

        return binding.root
    }

    private fun chairClicked(chairnumber: Int){
        val action = AlbertLienartstraatCoworkingFragmentDirections.actionAlbertLienartstraatCoworkingFragmentToCoworkingRecapFragment()
        action.seatNumber = chairnumber
        action.chamberName = "Hier.vanboven" //TODO: adapt viewmodel and use input data
        action.date = System.currentTimeMillis()
        action.locationName = "Albert Lienartstraat"
        action.timeslot = "Voormiddag"
        findNavController().navigate(action)
        binding.viewModel!!.onGreenChairClickedComplete()
    }

}