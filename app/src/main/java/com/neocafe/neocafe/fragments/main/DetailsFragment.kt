package com.neocafe.neocafe.fragments.main

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.databinding.FragmentDetailsBinding
import com.neocafe.neocafe.entities.menu.responses.ExtraItem
import com.neocafe.neocafe.entities.menu.responses.Menu
import com.neocafe.neocafe.entities.order.Basket
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.utils.Constants
import com.neocafe.neocafe.viewModels.MenuViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModel<MenuViewModel>()
    private val popularsAdapter = MenuRvAdapter()

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
        binding.additionsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.additionsRv.adapter = popularsAdapter
        viewModel.getPopulars(Constants.brancheId)
        getPopularsResponse()

        val data = arguments?.getSerializable("key") as Menu

        addToBasket(data)

        Glide.with(binding.imageImg).load(data.image).into(binding.imageImg)
        binding.nameTxt.text = data.name
        binding.amountTxt.text = data.amount.toString()
        binding.descriptionTxt.text = data.description
        binding.totalPriceTxt.text = data.price.replace(".00", "") + " c"
        if(data.extra_product.isEmpty()){
            binding.coffeOptions.visibility = View.GONE
        }else{
            binding.coffeOptions.visibility = View.VISIBLE
        }

        //Test
        val milks = data.extra_product.filter { it.type_extra_product.contains("Milk", ignoreCase = true)}
        val sirops = data.extra_product.filter { it.type_extra_product.contains("Syrop", ignoreCase = true)}

        val params = RadioGroup.LayoutParams(
            RadioGroup.LayoutParams.WRAP_CONTENT,
            RadioGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            // Set margins (adjust values as needed)
            setMargins(0, 8, 0, 8) // Left, Top, Right, Bottom
        }
        for (milk in milks) {
            val radioButton = RadioButton(requireContext())
            radioButton.text = milk.name + "  " + milk.price.replace(".00", "") + " с"
            radioButton.textSize = 16F
            radioButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.client_main))
            radioButton.layoutParams = params
            radioButton.tag = milk
            binding.radioMilkGroup.addView(radioButton)
            println(milk.type_extra_product)
        }

        for (sirop in sirops) {
            val checkBox = CheckBox(requireContext())
            checkBox.text = sirop.name + "  " + sirop.price.replace(".00", "") + " c"
            checkBox.layoutParams = params
            checkBox.textSize = 16F
            checkBox.setTextColor(ContextCompat.getColor(requireContext(), R.color.client_main))
            checkBox.tag = sirop
            binding.sirposContainer.addView(checkBox)
        }

        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
            bottomNavigationView?.visibility = View.VISIBLE
        }
    }

    private fun getPopularsResponse(){
        viewModel.getPopularsResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                it.data?.let { it1 -> popularsAdapter.setMenuList(it1) }
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addToBasket(menu: Menu){
        binding.amountTxt.text = menu.amount.toString()
        binding.addItemBtn.setOnClickListener {
            if(menu.amount < 9){
                menu.amount ++
                binding.amountTxt.text = menu.amount.toString()
            }
        }

        binding.lessItemBtn.setOnClickListener {
            if(menu.amount > 0){
                menu.amount--
                binding.amountTxt.text = menu.amount.toString()
            }
        }

        binding.addBtn.setOnClickListener {
            val selectedMilkRadioButton = binding.radioMilkGroup.findViewById<RadioButton>(binding.radioMilkGroup.checkedRadioButtonId)

            val menuMilk = selectedMilkRadioButton?.tag as? ExtraItem
            if(menuMilk == null){
                Basket.addMenu(menu)
            }else{
                menu.extraProduct = menuMilk
                Basket.addMenuWithExtra(menu)
            }
            addedToBasket()
        }

    }

    private fun addedToBasket(){
        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_added_to_basket)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val okBtn = dialogScreen.findViewById<Button>(R.id.okBtn)

        okBtn.setOnClickListener {
            dialogScreen.dismiss()
            findNavController().navigateUp()
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView?.visibility = View.VISIBLE
        }
        dialogScreen.show()

    }


}