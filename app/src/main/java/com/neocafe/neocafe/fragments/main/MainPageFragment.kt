package com.neocafe.neocafe.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentMainPageBinding
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.utils.TestData

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
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
        val adapter = MenuRvAdapter()
        binding.popularsRv.layoutManager = LinearLayoutManager(requireContext())
        adapter.setMenuList(TestData.listDesert)

        adapter.clickToDetails = {
            val bundle = Bundle()
            bundle.putSerializable("key", it)
            findNavController().navigate(R.id.action_mainPageFragment_to_detailsFragment, bundle)
        }

        binding.popularsRv.adapter = adapter
        binding.moreBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment)
        }
    }

}