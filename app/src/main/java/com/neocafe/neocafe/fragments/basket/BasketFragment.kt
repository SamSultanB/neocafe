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
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.databinding.FragmentBasketBinding
import com.neocafe.neocafe.entities.order.Basket

class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding

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
            dialogScreen.dismiss()
        }

        writeOFFBtn.setOnClickListener {
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