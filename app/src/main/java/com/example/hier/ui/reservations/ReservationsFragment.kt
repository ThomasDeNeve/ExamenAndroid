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
import com.example.hier.R
import com.example.hier.databinding.FragmentReservationsBinding
import org.koin.android.ext.android.inject
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
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            (activity as AppCompatActivity).supportActionBar?.title = "Mijn reservaties"
        } catch (e: java.lang.ClassCastException) {
            Log.i("classcastexception", e.stackTraceToString())
        }
        createTable(view)
    }

    @SuppressLint("SimpleDateFormat")
    private fun createTable(view: View) {
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val table = view.findViewById<TableLayout>(R.id.reservations_table)

        for ((count, reservation) in viewModel.reservations.withIndex()) {
            val row = TableRow(context)
            row.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

            if (count % 2 == 1) {
                row.setBackgroundColor(Color.LTGRAY)
            }

            createAndAddTextView(formatter.format(Date(reservation.from)), row)
            createAndAddTextView(reservation.room, row)

            table.addView(row)
        }
    }

    private fun createAndAddTextView(reservation: String, row: TableRow) {
        val text = TextView(context)
        text.text = reservation
        text.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 4F)
        row.addView(text)
    }
}
