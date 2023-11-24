package com.neocafe.neocafe.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentDetailsBinding
import com.neocafe.neocafe.utils.Menu

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView?.visibility = View.GONE

        val menu = arguments?.getSerializable("key") as Menu
        binding.imageImg.setImageResource(menu.image)
        binding.nameTxt.text = menu.name
        binding.descriptionTxt.text = menu.description
        binding.amountTxt.text = menu.amount.toString()

        binding.addItemBtn.setOnClickListener {
            menu.amount++
            binding.amountTxt.text = menu.amount.toString()
        }
        binding.lessItemBtn.setOnClickListener {
            menu.amount--;
            binding.amountTxt.text = menu.amount.toString()
        }

        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
            bottomNavigationView?.visibility = View.VISIBLE
        }
    }

}