package com.neocafe.neocafe.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.databinding.FragmentMainPageBinding
import com.neocafe.neocafe.entities.branches.Branche
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.utils.Constants
import com.neocafe.neocafe.utils.SpinnerBranchesAdapter
import com.neocafe.neocafe.viewModels.MenuViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private val viewModel by viewModel<MenuViewModel>()
    private val menuAdapter by inject<MenuRvAdapter>()
    private lateinit var spinnerAdapter: SpinnerBranchesAdapter
    private val searchViewAdapter by inject<MenuRvAdapter>()


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
        if(Constants.menu.isEmpty()){
            viewModel.getAllMenu(Constants.brancheId)
            getAllMenuResponse()
        }
        binding.searchMenuRv.layoutManager = LinearLayoutManager(requireContext())
        binding.searchMenuRv.adapter = searchViewAdapter

        setUpSearch()

        viewModel.getAllBranches()
        branchesResponse()
        binding.popularsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.popularsRv.adapter = menuAdapter

        menuAdapter.clickToDetails = {
            val bundle = Bundle()
            bundle.putSerializable("key", it)
            findNavController().navigate(R.id.action_mainPageFragment_to_detailsFragment, bundle)
        }

        viewModel.getPopulars(Constants.brancheId)
        getPopularsResponse()
        navigateToCategories()


        binding.toNotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_notificationsFragment)
        }
        binding.moreBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key", 0)
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment, bundle)
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
            val bundle = Bundle()
            bundle.putInt("key", 0)
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment, bundle)
        }
        binding.category2Img.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key", 1)
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment, bundle)
        }
        binding.category3Img.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key", 2)
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment, bundle)
        }
        binding.category4Img.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key", 3)
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment, bundle)
        }
        binding.category5Img.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key", 4)
            findNavController().navigate(R.id.action_mainPageFragment_to_menuPageFragment, bundle)
        }
    }

    private fun branchesResponse(){
        viewModel.getAllBranchesResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                it.data?.let { it1 ->
                    spinnerBranches(it1)
                    selectBranche()
                }
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun selectBranche(){
        binding.spinnerBranches.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                val selectedValue: Branche = parentView?.getItemAtPosition(position) as Branche
                Constants.brancheId = selectedValue.id
                Constants.selectedItemPosition = position
                viewModel.getCategories(Constants.brancheId)
                getCategoriesResponse()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun spinnerBranches(branches: List<Branche>) {
        spinnerAdapter = SpinnerBranchesAdapter(requireContext(), branches)
        binding.spinnerBranches.adapter = spinnerAdapter
        binding.spinnerBranches.setSelection(Constants.selectedItemPosition)
    }

    private fun setUpSearch(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                var filteredList = Constants.menu.filter { it.name.contains(newText, ignoreCase = true) || it.slug.contains(newText, ignoreCase = true) }
                if(newText.isEmpty()){
                    binding.searchMenuRv.visibility = View.GONE
                    binding.mainContent.visibility = View.VISIBLE
                    binding.searchView.clearFocus()
                }else{
                    binding.searchMenuRv.visibility = View.VISIBLE
                    binding.mainContent.visibility = View.GONE
                    searchViewAdapter.setMenuList(filteredList)
                }
                return false
            }
        })
    }

    private fun getAllMenuResponse(){
        viewModel.getAllMenuResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                it.data?.let { it1 -> Constants.menu = it1 }
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}