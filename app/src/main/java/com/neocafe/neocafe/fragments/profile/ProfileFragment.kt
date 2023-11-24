package com.neocafe.neocafe.fragments.profile

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.LastOrdersRv
import com.neocafe.neocafe.databinding.FragmentProfileBinding
import com.neocafe.neocafe.utils.HistoryOfOrdersItem
import com.neocafe.neocafe.utils.TestData

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bonusImg.setOnClickListener {
            callAlertDialog()
        }
        binding.editProfileBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_profileEditFragment)
        }
        binding.notificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_notificationsFragment)
        }
        binding.logoutBtn.setOnClickListener {
            activity?.finish()
        }


        //test
        val adapterActual = LastOrdersRv()
        adapterActual.lastOrders = listOf(HistoryOfOrdersItem(R.drawable.filial_test1, "NeoCafe Toktogula", "basket, coffe, tea", "now"))
        binding.actualOrdersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.actualOrdersRv.adapter = adapterActual

        val adapterPast = LastOrdersRv()
        adapterPast.lastOrders = TestData.lastOrders
        binding.lastOrdersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.lastOrdersRv.adapter = adapterPast



    }

    private fun callAlertDialog(){
        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_bonus)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val okBtn = dialogScreen.findViewById<Button>(R.id.okBtn)
        okBtn.setOnClickListener {
            dialogScreen.dismiss()
        }
        dialogScreen.show()
    }

}