package com.neocafe.neocafe.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.adapters.MenuVpAdapter
import com.neocafe.neocafe.databinding.FragmentMenuPageBinding
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.viewModels.MenuViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuPageFragment : Fragment() {

    private lateinit var binding: FragmentMenuPageBinding
    private val viewModel by viewModel<MenuViewModel>()
    private lateinit var adapterVp: MenuVpAdapter
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
        adapterVp = MenuVpAdapter(childFragmentManager, lifecycle)
        binding.toNotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_menuPageFragment_to_notificationsFragment)
        }
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getCategories()

        getCategoriesResponse()
    }


    private fun getCategoriesResponse(){
        val tablayout = binding.categoriesTab
        viewModel.getCategoriesResponse.observe(viewLifecycleOwner, Observer {
            if(it is Resource.Success){
                it.data?.let { it1 ->
                    adapterVp.setData(it1)
                    binding.viewPager.adapter = adapterVp
                    TabLayoutMediator(tablayout, binding.viewPager){tab, position ->
                        tab.text = it1[position].name
                        viewModel.getMenu(it1[position].slug)
                    }.attach()
                }
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}