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

        viewmodel.eventChairClicked.observe(this, Observer { chairClick ->
            if (chairClick){
                val action = AlbertLienartstraatCoworkingFragmentDirections.actionAlbertLienartstraatCoworkingFragmentToCoworkingRecapFragment()
                action.seatNumber = viewmodel.clickedSeatId.value!!
                action.chamberName = viewmodel.chamber.value!!
                action.date = viewmodel.date.value!!
                action.locationName = "Albert Lienartstraat"
                findNavController().navigate(action)
                viewmodel.onGreenChairClickedComplete()
            }
        })

        return binding.root
    }
}