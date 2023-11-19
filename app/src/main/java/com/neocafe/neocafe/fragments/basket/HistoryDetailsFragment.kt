package com.neocafe.neocafe.fragments.basket

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentHistoryDetailsBinding

class HistoryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentHistoryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fullPriceTxt.paintFlags = binding.fullPriceTxt.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}