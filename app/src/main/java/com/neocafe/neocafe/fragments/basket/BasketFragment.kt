package com.neocafe.neocafe.fragments.basket

import android.app.Dialog
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.databinding.FragmentBasketBinding
import com.neocafe.neocafe.entities.order.Basket
import com.neocafe.neocafe.entities.order.requests.OrderItem
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.utils.Constants
import com.neocafe.neocafe.viewModels.BasketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private val viewModel by viewModel<BasketViewModel>()

    private var takeaway = true
    private var orderBtnCycle = 1
    private var usedBonuses = "0"

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
        viewModel.profile()
        getProfileResponse()
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
            if(orderBtnCycle == 1){
                callAlertDialog()
                orderBtnCycle = 2
            }else if(orderBtnCycle == 2){
                order(usedBonuses)
                orderBtnCycle = 1
            }
        }

        Basket.totalPriceInBasket.observe(viewLifecycleOwner, Observer {

            binding.ordersListScreen.priceWithDiscountTxt.text = "$it c"
            binding.ordersListScreen.fullPriceTxt.text = "$it c"

        })

        binding.ordersListScreen.fullPriceTxt.isVisible = true

        binding.ordersListScreen.takeAwayBtn.setOnClickListener {
            takeaway = true
            binding.ordersListScreen.takeAwayBtn.backgroundTintList = resources.getColorStateList(R.color.button_background)
            binding.ordersListScreen.takeAwayBtn.setTextColor(resources.getColorStateList(R.color.white))
            binding.ordersListScreen.inShopBtn.backgroundTintList = resources.getColorStateList(R.color.edit_text_background)
            binding.ordersListScreen.inShopBtn.setTextColor(resources.getColorStateList(R.color.black))
        }
        binding.ordersListScreen.inShopBtn.setOnClickListener {
            takeaway = false
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
                successfullOrder()
            }else if(it is Resource.Error){
                println(it.message)
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getProfileResponse(){
        viewModel.profileResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                Constants.bonuse = it.data?.bonuses!!.toBigDecimal().toInt()
            }else if(it is Resource.Error){
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
        val editText = dialogScreen.findViewById<TextInputEditText>(R.id.bonusEditTxt)
        val bonus = dialogScreen.findViewById<TextView>(R.id.bonusTxt)
        bonus.text = Constants.bonuse.toString()
        val writeOFFBtn = dialogScreen.findViewById<Button>(R.id.yesBtn)
        val cancelBtn = dialogScreen.findViewById<Button>(R.id.noBtn)

         cancelBtn.setOnClickListener {
             order("0")
             dialogScreen.dismiss()
        }

        writeOFFBtn.setOnClickListener {
            if(editText.text!!.isEmpty()){
                usedBonuses = "0"
            }else{
                usedBonuses = editText.text.toString()
                binding.ordersListScreen.priceWithDiscountTxt.text = (Basket.totalPrice - usedBonuses.toInt()).toString()
            }
            dialogScreen.dismiss()
        }

        dialogScreen.show()
    }

    private fun successfullOrder(){
        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_successfull_order)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val okBtn = dialogScreen.findViewById<Button>(R.id.okBtn)

        okBtn.setOnClickListener {
            dialogScreen.dismiss()
            viewModel.orderResponse.postValue(Resource.Loading())
            Basket.totalPrice = 0
            Basket.order.clear()
            Basket.orderForRequest.clear()
            binding.ordersListScreen.root.isVisible = false
            binding.emptyScreen.root.isVisible = true
        }
        dialogScreen.show()

    }

    private fun order(usedBonuses: String){
        var orderType = ""
        if(takeaway == true){
            orderType = "takeaway"
        }else{
            orderType = "inplace"
        }
        val order = OrderItem(orderType, "new", Constants.brancheId, null, usedBonuses.replace(".00",""), (Basket.totalPrice - usedBonuses.toInt()).toString(), Basket.orderForRequest.values.toList())
        viewModel.order(order)
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