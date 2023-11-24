package com.neocafe.neocafe.fragments.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.LastOrdersRv
import com.neocafe.neocafe.databinding.FragmentHistoryOfOrdersBinding
import com.neocafe.neocafe.utils.HistoryOfOrdersItem
import com.neocafe.neocafe.utils.TestData

class HistoryOfOrdersFragment : Fragment() {

    private lateinit var binding: FragmentHistoryOfOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryOfOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.toNotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_historyOfOrdersFragment_to_historyDetailsFragment)
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

}