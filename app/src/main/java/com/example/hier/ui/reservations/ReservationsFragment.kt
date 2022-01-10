package com.example.hier.ui.reservations

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TableRow.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.hier.R
import com.example.hier.adapters.ReservationsAdapter
import com.example.hier.databinding.FragmentReservationsBinding
import org.koin.android.ext.android.inject
import java.lang.ClassCastException
import java.text.SimpleDateFormat
import java.util.Date

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

        viewModel.response.observe(viewLifecycleOwner, Observer {
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

    private fun createAndAddTextView(reservation: String, row: TableRow) {
        val text = TextView(context)
        text.text = reservation
        text.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 4F)
        row.addView(text)
    }
}
