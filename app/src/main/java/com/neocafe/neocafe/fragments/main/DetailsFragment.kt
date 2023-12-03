package com.neocafe.neocafe.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentDetailsBinding
import com.neocafe.neocafe.entities.menu.Menu

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

        val data = arguments?.getSerializable("key") as Menu

        Glide.with(binding.imageImg).load(data.image).into(binding.imageImg)
        binding.nameTxt.text = data.name
        binding.descriptionTxt.text = data.description

        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
            bottomNavigationView?.visibility = View.VISIBLE

        }
    }

}