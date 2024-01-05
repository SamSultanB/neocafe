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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.LastOrdersDetailsRv
import com.neocafe.neocafe.databinding.FragmentHistoryDetailsBinding
import com.neocafe.neocafe.entities.order.requests.OrderItem
import com.neocafe.neocafe.entities.order.responses.Order
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.utils.Constants
import com.neocafe.neocafe.viewModels.BasketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

class HistoryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentHistoryDetailsBinding
    private val adapter = LastOrdersDetailsRv()
    private val viewModel by viewModel<BasketViewModel>()
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
            if(item.extra_product != null){
                totalPrice += item.extra_product_detail!!.price!!.toDouble().roundToInt()*item.extra_product_quantity
            }
        }
        binding.fullPriceTxt.text = totalPrice.toString()
        binding.priceWithDiscountTxt.text = (totalPrice - order.bonuses_used.toDouble().roundToInt()).toString()
        binding.orderedItemsRv.layoutManager = LinearLayoutManager(requireContext())
        adapter.setItems(order.items)
        binding.bonusTxt.text = order.bonuses_used.replace(".00", "")
        binding.orderedItemsRv.adapter = adapter

        adapter.clickToIncreament = {
            if(order.items[it].menu_quantity < 9){
                order.items[it].menu_quantity ++
                totalPrice += order.items[it].menu_detail.price.toDouble().roundToInt()
                if(order.items[it].extra_product != null){
                    order.items[it].extra_product_quantity ++
                    totalPrice += order.items[it].extra_product_detail!!.price!!.toDouble().roundToInt()
                }
            }
            binding.fullPriceTxt.text = totalPrice.toString()
            binding.priceWithDiscountTxt.text = (totalPrice - order.bonuses_used.toDouble().roundToInt()).toString()
        }

        adapter.clickToDecreament = {
            if(order.items[it].menu_quantity > 0){
                order.items[it].menu_quantity --
                totalPrice -= order.items[it].menu_detail.price.toDouble().roundToInt()
                if(order.items[it].extra_product != null){
                    order.items[it].extra_product_quantity --
                    totalPrice -= order.items[it].extra_product_detail!!.price!!.toDouble().roundToInt()
                }
            }
            binding.fullPriceTxt.text = totalPrice.toString()
            binding.priceWithDiscountTxt.text = (totalPrice - order.bonuses_used.toDouble().roundToInt()).toString()
        }

        binding.addressAndTimeTxt.text = order.branch.name + ", " + convertToCustomFormat(order.created)

        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.fullPriceTxt.paintFlags = binding.fullPriceTxt.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        binding.repeatOrderBtn.setOnClickListener {
            repeatOrderAlert(order, totalPrice)
        }
        orderResponse()

    }

    private fun orderResponse(){
        viewModel.orderResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                successfullOrder()
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun repeatOrderAlert(order: Order, totalPrice: Int){

        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_repeat_order)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val yesBtn = dialogScreen.findViewById<Button>(R.id.yesBtn)
        val noBtn = dialogScreen.findViewById<Button>(R.id.noBtn)

        noBtn.setOnClickListener {
            dialogScreen.dismiss()
        }
        yesBtn.setOnClickListener {
            repeatOrder(order, totalPrice)
            dialogScreen.dismiss()
        }
        dialogScreen.show()

    }

    private fun repeatOrder(order: Order, totalPrice: Int){
        val orderItem = OrderItem("takeaway", "new", Constants.brancheId, null, order.bonuses_used, (totalPrice - order.bonuses_used.toDouble().roundToInt()).toString(), order.items)
        viewModel.order(orderItem)
    }

    private fun successfullOrder(){
        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_successfull_order)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val okBtn = dialogScreen.findViewById<Button>(R.id.okBtn)

        okBtn.setOnClickListener {
            dialogScreen.dismiss()
            viewModel.orderResponse.postValue(Resource.Loading())
            findNavController().navigateUp()
        }
        dialogScreen.show()

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