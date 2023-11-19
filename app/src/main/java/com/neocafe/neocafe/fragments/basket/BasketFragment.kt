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
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentBasketBinding

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
        binding.ordersListScreen.fullPriceTxt.paintFlags = binding.ordersListScreen.fullPriceTxt.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        binding.ordersListScreen.orderBtn.setOnClickListener {
            callAlertDialog()
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

}