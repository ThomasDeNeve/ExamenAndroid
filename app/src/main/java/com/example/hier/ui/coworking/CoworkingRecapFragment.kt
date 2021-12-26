package com.example.hier.ui.coworking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hier.R
import com.example.hier.databinding.FragmentCoworkingRecapBinding
import com.example.hier.util.Status
import kotlinx.coroutines.launch
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
        binding.viewModel = viewModel

        viewModel.setLocation(CoworkingRecapFragmentArgs.fromBundle(requireArguments()).locationName)
        viewModel.setChamber(CoworkingRecapFragmentArgs.fromBundle(requireArguments()).chamberName)
        viewModel.setSeatId(CoworkingRecapFragmentArgs.fromBundle(requireArguments()).seatNumber)
        viewModel.setDate(CoworkingRecapFragmentArgs.fromBundle(requireArguments()).date)

        binding.submitButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.onSubmit()

                if (viewModel.response.status == Status.SUCCESS) {
                    if (viewModel.response.data.equals("Ok"))
                        Toast.makeText(context, "Uw reservatie is gelukt!", Toast.LENGTH_LONG)
                            .show()
                } else
                    Toast.makeText(
                        context,
                        "Uw reservatie is helaas mislukt, probeer het later opnieuw",
                        Toast.LENGTH_LONG
                    ).show()
            }
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