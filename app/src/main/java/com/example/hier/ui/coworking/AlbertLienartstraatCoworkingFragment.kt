package com.example.hier.ui.coworking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hier.databinding.FragmentAlbertLienartstraatCoworkingBinding

class AlbertLienartstraatCoworkingFragment : Fragment() {
    private lateinit var binding: FragmentAlbertLienartstraatCoworkingBinding

    private val viewmodel: ALCViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbertLienartstraatCoworkingBinding.inflate(inflater, container, false)

        return binding.root
    }



}