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
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.databinding.FragmentCategoryListBinding
import com.neocafe.neocafe.entities.categories.Category
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.viewModels.MenuViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryListFragment : Fragment() {

    private lateinit var binding: FragmentCategoryListBinding
    private val viewModel by viewModel<MenuViewModel>()
    private val menuRvAdapter by inject<MenuRvAdapter>()

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
        binding.menuListRv.layoutManager = LinearLayoutManager(requireContext())
        binding.menuListRv.adapter = menuRvAdapter

        val data = arguments?.getSerializable("key") as Category
        binding.categoryTxt.text = data.name

        viewModel.getMenu(data.slug)
        getMenuResponse()

        menuRvAdapter.clickToDetails = {
            val bundle = Bundle()
            bundle.putSerializable("key", it)
            findNavController().navigate(R.id.action_menuPageFragment_to_detailsFragment, bundle)
        }
    }

    private fun getMenuResponse(){
        viewModel.getMenuResponse.observe(viewLifecycleOwner, Observer {
            if(it is Resource.Success){
                it.data?.let { it1 -> menuRvAdapter.setMenuList(it1) }
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}