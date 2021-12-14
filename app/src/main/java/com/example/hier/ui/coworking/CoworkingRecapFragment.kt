package com.example.hier.ui.coworking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hier.R
import com.example.hier.databinding.FragmentCoworkingRecapBinding
import com.example.hier.repository.ReservationRepository
import org.koin.android.ext.android.inject

class CoworkingRecapFragment : Fragment() {
    private lateinit var binding: FragmentCoworkingRecapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_coworking_recap,
            container,
            false
        )
        val viewModel: CoworkingRecapViewModel by inject()
        binding.viewModel=viewModel

        viewModel.setLocation(CoworkingRecapFragmentArgs.fromBundle(requireArguments()).locationName)
        viewModel.setChamber(CoworkingRecapFragmentArgs.fromBundle(requireArguments()).chamberName)
        viewModel.setSeatId(CoworkingRecapFragmentArgs.fromBundle(requireArguments()).seatNumber)
        viewModel.setDate(CoworkingRecapFragmentArgs.fromBundle(requireArguments()).date)

        binding.submitButton.setOnClickListener{
            viewModel.onSubmit()
        }

        viewModel.eventSubmit.observe(viewLifecycleOwner, Observer { submit ->
            if (submit) {
                findNavController().navigate(CoworkingRecapFragmentDirections.actionCoworkingRecapFragmentToReservationsFragment())
                viewModel.onSubmitComplete()
            }
        })

        return binding.root
    }
}