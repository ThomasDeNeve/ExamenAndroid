package com.example.hier.ui.makeChoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hier.R
import com.example.hier.databinding.FragmentChoiceBinding
import com.example.hier.ui.login.LoginFragmentDirections

class ChoiceCoworkingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentChoiceBinding.inflate(inflater, container, false)
        binding.btnLocOne.setOnClickListener {
            //TODO: add date as argument
            val directions = ChoiceCoworkingFragmentDirections.actionChoiceCoworkingFragmentToAlbertLienartstraatCoworkingFragment()
            findNavController().navigate(directions)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.kies_locatie)
    }
}