package com.neocafe.neocafe.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentPhoneNumberBinding
import com.neocafe.neocafe.databinding.SpinnerItemBinding
import com.neocafe.neocafe.utils.Countries
import com.neocafe.neocafe.utils.SpinnerAdapter
import com.neocafe.neocafe.utils.SpinnerItem


class PhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentPhoneNumberBinding
    private lateinit var spinnerAdapter: SpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhoneNumberBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinnerCountryCode()
        setCountryCode()
        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.getCodeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_phoneNumberFragment_to_otpLoginFragment)
        }

    }


    private fun setCountryCode(){
        binding.countryCode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                val selectedValue: SpinnerItem = parentView?.getItemAtPosition(position) as SpinnerItem
                binding.chosenCountry.text = selectedValue.countryCode
                binding.phoneNumber.hint = selectedValue.hint
                binding.phoneNumber.setMask(selectedValue.mask)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.chosenCountry.text = "+996"
            }
        }
    }

    private fun spinnerCountryCode() {
        spinnerAdapter = SpinnerAdapter(requireContext(), Countries.countries)
        binding.countryCode.adapter = spinnerAdapter
    }

}