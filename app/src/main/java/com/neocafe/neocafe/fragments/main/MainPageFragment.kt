package com.neocafe.neocafe.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentMainPageBinding
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.viewModels.MenuViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private val viewModel by viewModel<MenuViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.popularsRv.layoutManager = LinearLayoutManager(requireContext())

        binding.toNotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_notificationsFragment)
        }
        binding.moreBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment)
        }
    }

}