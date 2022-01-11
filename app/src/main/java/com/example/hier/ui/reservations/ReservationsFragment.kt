package com.example.hier.ui.reservations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hier.adapters.ReservationsAdapter
import com.example.hier.databinding.FragmentReservationsBinding
import org.koin.android.ext.android.inject
import java.lang.ClassCastException

class ReservationsFragment : Fragment() {

    val viewModel: ReservationsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentReservationsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        val adapter = ReservationsAdapter()
        binding.reservationsList.adapter = adapter

        viewModel.response.observe(viewLifecycleOwner, {
            it?.let{
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            (activity as AppCompatActivity).supportActionBar?.title = "Mijn reservaties"
        } catch (e: ClassCastException) {
            Log.i("classcastexception", e.stackTraceToString())
        }
    }
}
