package com.neocafe.neocafe.fragments.main

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.adapters.SelectBranchRvAdapter
import com.neocafe.neocafe.databinding.FragmentMainPageBinding
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.viewModels.MenuViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private val viewModel by viewModel<MenuViewModel>()
    private val menuAdapter by inject<MenuRvAdapter>()
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
        binding.popularsRv.adapter = menuAdapter

        menuAdapter.clickToDetails = {
            val bundle = Bundle()
            bundle.putSerializable("key", it)
            findNavController().navigate(R.id.action_mainPageFragment_to_detailsFragment, bundle)
        }

        viewModel.getPopulars()
        getPopularsResponse()
        viewModel.getCategories()
        getCategoriesResponse()

        navigateToCategories()

        binding.toNotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_notificationsFragment)
        }
        binding.moreBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment)
        }
    }

    private fun getPopularsResponse(){
        viewModel.getPopularsResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                it.data?.let { it1 -> menuAdapter.setMenuList(it1) }
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getCategoriesResponse(){
        viewModel.getCategoriesResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                it.data?.let { it1 ->
                    Glide.with(binding.category1Img).load(it1[0].image).into(binding.category1Img)
                    binding.category1Txt.text = it1[0].name

                    Glide.with(binding.category2Img).load(it1[1].image).into(binding.category2Img)
                    binding.category2Txt.text = it1[1].name

                    Glide.with(binding.category3Img).load(it1[2].image).into(binding.category3Img)
                    binding.category3Txt.text = it1[2].name

                    Glide.with(binding.category4Img).load(it1[3].image).into(binding.category4Img)
                    binding.category4Txt.text = it1[3].name

                    Glide.with(binding.category5Img).load(it1[4].image).into(binding.category5Img)
                    binding.category5Txt.text = it1[4].name
                }
            }else if (it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToCategories(){
        binding.category1Img.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment)
        }
        binding.category2Img.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment)
        }
        binding.category3Img.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment)
        }
        binding.category4Img.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment)
        }
        binding.category5Img.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment)
        }
    }

    private fun branchesResponse(adapter: SelectBranchRvAdapter){
        viewModel.getAllBranchesResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                it.data?.let { it1 -> adapter.setList(it1) }
                Toast.makeText(requireContext(), "Filials are here!", Toast.LENGTH_SHORT).show()
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun chosenResponse(){
        viewModel.chooseBrancheResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                Toast.makeText(requireContext(), "Choosen!", Toast.LENGTH_SHORT).show()
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}