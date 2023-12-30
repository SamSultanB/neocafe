package com.neocafe.neocafe.fragments.basket

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.adapters.LastOrdersDetailsRv
import com.neocafe.neocafe.databinding.FragmentHistoryDetailsBinding
import com.neocafe.neocafe.entities.order.responses.Order
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

class HistoryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentHistoryDetailsBinding
    private val adapter = LastOrdersDetailsRv()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val order = arguments?.getSerializable("key") as Order
        var totalPrice = 0
        for (item in order.items){
            totalPrice += item.menu_detail.price.toDouble().roundToInt()*item.menu_quantity
        }
        binding.fullPriceTxt.text = totalPrice.toString()
        binding.orderedItemsRv.layoutManager = LinearLayoutManager(requireContext())
        adapter.setItems(order.items)
        binding.orderedItemsRv.adapter = adapter

        binding.addressAndTimeTxt.text = order.branch.name + ", " + convertToCustomFormat(order.created)

        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.fullPriceTxt.paintFlags = binding.fullPriceTxt.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

    }

    fun convertToCustomFormat(dateTime: String): String {
        // Define a custom formatter
        val dateTime2 = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

        return dateTime2.atOffset(ZoneOffset.UTC)
            .atZoneSameInstant(ZoneOffset.ofHours(6))
            .format(formatter)
    }

}