package com.example.hier.ui.coworking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
        binding.viewModel = viewmodel

        viewmodel.eventChairClicked.observe(this, Observer { chairClick ->
            if (chairClick) {
                val action =
                    AlbertLienartstraatCoworkingFragmentDirections.actionAlbertLienartstraatCoworkingFragmentToCoworkingRecapFragment()
                action.seatNumber = viewmodel.clickedSeatId.value!!
                action.chamberName = viewmodel.chamber.value!!
                action.date = viewmodel.date.value!!
                action.locationName = "Albert Lienartstraat"
                findNavController().navigate(action)
                viewmodel.onGreenChairClickedComplete()
            }
        })

        viewmodel.listOfChairs.forEach { item ->
            item.observe(this, Observer {
                updateChairs(viewmodel)
            })
        }

        return binding.root
    }

    private fun updateChairs(viewmodel: ALCViewModel) {
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
        binding.chair14.visibility =
            if (viewmodel.chair14reserved.value!!) View.GONE else View.VISIBLE
        binding.chair15.visibility =
            if (viewmodel.chair15reserved.value!!) View.GONE else View.VISIBLE
        binding.chair16.visibility =
            if (viewmodel.chair16reserved.value!!) View.GONE else View.VISIBLE
    }
}