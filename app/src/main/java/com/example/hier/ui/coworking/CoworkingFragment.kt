package com.example.hier.ui.coworking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hier.databinding.FragmentCoworkingBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CoworkingFragment : Fragment() {
    private lateinit var binding: FragmentCoworkingBinding
    private val viewModel: CoworkingViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoworkingBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        viewModel.eventChairClicked.observe(viewLifecycleOwner, { chairClick ->
            if (chairClick) {
                val action =
                    CoworkingFragmentDirections.actionAlbertLienartstraatCoworkingFragmentToCoworkingRecapFragment()
                action.seatNumber = viewModel.clickedSeatId.value!!
                action.chamberName = viewModel.chamber.value!!
                action.date = viewModel.date.value!!
                action.locationName = "Albert Lienartstraat"
                findNavController().navigate(action)
                viewModel.onGreenChairClickedComplete()
            }
        })

        viewModel.date.observe(viewLifecycleOwner, { newDate ->
            lifecycleScope.launch { viewModel.checkAvailability(newDate) }
        })

        //viewModel.setInitialDate()

        viewModel.listOfChairs.forEach { item ->
            item.observe(viewLifecycleOwner, {
                updateChairs(viewModel)
            })
        }

        return binding.root
    }

    private fun updateChairs(viewmodel: CoworkingViewModel) {
        binding.chair1.visibility =
            if (viewmodel.chair1reserved.value!!) View.GONE else View.VISIBLE
        binding.chair2.visibility =
            if (viewmodel.chair2reserved.value!!) View.GONE else View.VISIBLE
        binding.chair3.visibility =
            if (viewmodel.chair3reserved.value!!) View.GONE else View.VISIBLE
        binding.chair4.visibility =
            if (viewmodel.chair4reserved.value!!) View.GONE else View.VISIBLE
        binding.chair5.visibility =
            if (viewmodel.chair5reserved.value!!) View.GONE else View.VISIBLE
        binding.chair6.visibility =
            if (viewmodel.chair6reserved.value!!) View.GONE else View.VISIBLE
        binding.chair7.visibility =
            if (viewmodel.chair7reserved.value!!) View.GONE else View.VISIBLE
        binding.chair8.visibility =
            if (viewmodel.chair8reserved.value!!) View.GONE else View.VISIBLE
        binding.chair9.visibility =
            if (viewmodel.chair9reserved.value!!) View.GONE else View.VISIBLE
        binding.chair10.visibility =
            if (viewmodel.chair10reserved.value!!) View.GONE else View.VISIBLE
        binding.chair11.visibility =
            if (viewmodel.chair11reserved.value!!) View.GONE else View.VISIBLE
        binding.chair12.visibility =
            if (viewmodel.chair12reserved.value!!) View.GONE else View.VISIBLE
        binding.chair13.visibility =
            if (viewmodel.chair13reserved.value!!) View.GONE else View.VISIBLE
    }
}
