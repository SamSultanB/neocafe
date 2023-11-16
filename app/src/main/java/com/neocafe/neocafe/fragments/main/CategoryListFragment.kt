package com.neocafe.neocafe.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentCategoryListBinding
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.utils.TestCategory
import com.neocafe.neocafe.utils.TestData

class CategoryListFragment : Fragment() {

    private lateinit var binding: FragmentCategoryListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getSerializable("key") as TestCategory
        val adapter = MenuRvAdapter()

        adapter.clickToDetails = {
            val bundle = Bundle()
            bundle.putSerializable("key", it)
            findNavController().navigate(R.id.action_menuPageFragment_to_detailsFragment, bundle)
        }

        binding.menuListRv.layoutManager = LinearLayoutManager(requireContext())
        adapter.setMenuList(data.products)
        binding.categoryTxt.text = data.name
        binding.menuListRv.adapter = adapter
    }

}