package com.neocafe.neocafe.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentMenuPageBinding
import com.neocafe.neocafe.utils.MenuVpAdapter

class MenuPageFragment : Fragment() {

    private lateinit var binding: FragmentMenuPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notificationBtn.setOnClickListener {
            findNavController().navigate(R.id.action_menuPageFragment_to_detailsFragment)
        }
        val tablayout = binding.categoriesTab
        binding.viewPager.adapter = MenuVpAdapter(parentFragmentManager, lifecycle)
        TabLayoutMediator(tablayout, binding.viewPager){tab, position ->
            when(position){
                0 -> tab.text = "Кофе"
                1 -> tab.text = "Десерты"
                2 -> tab.text = "Выпечка"
                3 -> tab.text = "Коктейли"
                4 -> tab.text = "Чай"
            }
        }.attach()
    }

}