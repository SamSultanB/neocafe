package com.neocafe.neocafe.fragments.basket

import android.app.Dialog
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.databinding.FragmentBasketBinding
import com.neocafe.neocafe.entities.order.Basket
import com.neocafe.neocafe.entities.order.requests.MenuItem
import com.neocafe.neocafe.entities.order.requests.OrderItem
import com.neocafe.neocafe.entities.order.responses.OrderExtraItem
import com.neocafe.neocafe.entities.order.responses.OrderMenu
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.viewModels.BasketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private val viewModel by viewModel<BasketViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ordersListScreen.basketRv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MenuRvAdapter()
        adapter.setMenuList(Basket.order.values.toList())
        binding.ordersListScreen.basketRv.adapter = adapter


        setScreen()

        binding.notificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_basketFragment_to_notificationsFragment)
        }

        binding.emptyScreen.toMenuBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key", 0)
            findNavController().navigate(R.id.action_basketFragment_to_menuPageFragment, bundle)
        }
        binding.ordersListScreen.fullPriceTxt.paintFlags = binding.ordersListScreen.fullPriceTxt.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        binding.ordersListScreen.orderBtn.setOnClickListener {
            callAlertDialog()
        }

        binding.ordersListScreen.priceWithDiscountTxt.text = Basket.totalPrice.toString() + " c"

        binding.ordersListScreen.takeAwayBtn.setOnClickListener {
            binding.ordersListScreen.takeAwayBtn.backgroundTintList = resources.getColorStateList(R.color.button_background)
            binding.ordersListScreen.takeAwayBtn.setTextColor(resources.getColorStateList(R.color.white))
            binding.ordersListScreen.inShopBtn.backgroundTintList = resources.getColorStateList(R.color.edit_text_background)
            binding.ordersListScreen.inShopBtn.setTextColor(resources.getColorStateList(R.color.black))
        }
        binding.ordersListScreen.inShopBtn.setOnClickListener {
            binding.ordersListScreen.takeAwayBtn.backgroundTintList = resources.getColorStateList(R.color.edit_text_background)
            binding.ordersListScreen.takeAwayBtn.setTextColor(resources.getColorStateList(R.color.black))
            binding.ordersListScreen.inShopBtn.backgroundTintList = resources.getColorStateList(R.color.button_background)
            binding.ordersListScreen.inShopBtn.setTextColor(resources.getColorStateList(R.color.white))
        }
        binding.ordersListScreen.addMoreBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key", 0)
            findNavController().navigate(R.id.action_basketFragment_to_menuPageFragment, bundle)
        }

        orderResponse()


    }

    private fun orderResponse(){
        viewModel.orderResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                Toast.makeText(requireContext(), "Order made!!", Toast.LENGTH_SHORT).show()
            }else if(it is Resource.Error){
                println(it.message)
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun callAlertDialog(){

        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_use_bonuse_suggestion)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val yesBtn = dialogScreen.findViewById<Button>(R.id.yesBtn)
        val noBtn = dialogScreen.findViewById<Button>(R.id.noBtn)

        noBtn.setOnClickListener {
            dialogScreen.dismiss()
        }
        yesBtn.setOnClickListener {
            dialogScreen.dismiss()
            writeOffBonus()
        }
        dialogScreen.show()

    }

    private fun writeOffBonus(){
        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_use_bonus)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val writeOFFBtn = dialogScreen.findViewById<Button>(R.id.yesBtn)
        val cancelBtn = dialogScreen.findViewById<Button>(R.id.noBtn)

         cancelBtn.setOnClickListener {
             viewModel.order(OrderItem(0, MenuItem("cappucino", "199"), 1, OrderExtraItem("moloko", "10"), 1, "0"))
             dialogScreen.dismiss()
        }

        writeOFFBtn.setOnClickListener {
            viewModel.order(OrderItem(0, MenuItem("cappucino", "199"), 1, OrderExtraItem("moloko", "10"), 1, "0"))
            dialogScreen.dismiss()
        }

        dialogScreen.show()
    }

    private fun setScreen(){
        if(!Basket.order.isEmpty()){
            binding.emptyScreen.root.isVisible = false
            binding.ordersListScreen.root.isVisible = true
        }else{
            binding.emptyScreen.root.isVisible = true
            binding.ordersListScreen.root.isVisible = false
        }
    }

}