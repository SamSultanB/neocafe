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
import com.neocafe.neocafe.adapters.MenuVpAdapter
import com.neocafe.neocafe.utils.TestData

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
        binding.toNotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_menuPageFragment_to_detailsFragment)
        }
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        val tablayout = binding.categoriesTab
        val items = TestData.list
        val adapter = MenuVpAdapter(childFragmentManager, lifecycle)
        adapter.setData(items)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(tablayout, binding.viewPager){tab, position ->
            tab.text = items[position].name
        }.attach()
    }

}