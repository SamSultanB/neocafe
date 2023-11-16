package com.neocafe.neocafe.fragments.auth.login

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentPhoneNumberBinding
import com.neocafe.neocafe.utils.Countries
import com.neocafe.neocafe.utils.SpinnerAdapter
import com.neocafe.neocafe.utils.SpinnerItem
import com.neocafe.neocafe.viewModels.AuthViewModel
import org.koin.android.ext.android.get


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
        val viewModel = AuthViewModel()
        spinnerCountryCode()
        setCountryCode()
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.getCodeBtn.setOnClickListener {
            val editText = binding.phoneNumberEditTxt.text.toString()
            val maskText = binding.phoneNumberEditTxt.maskString.toString()
            if (viewModel.validPhoneNumber(editText.length, maskText.length) == null){
                findNavController().navigate(R.id.action_phoneNumberFragment_to_otpLoginFragment)
            }else{
                binding.errorTxt.visibility = View.VISIBLE
                binding.phoneNumberEditTxt.setTextColor(Color.RED)
                binding.chosenCountry.setTextColor(Color.RED)
                binding.errorTxt.text = viewModel.validPhoneNumber(editText.length, maskText.length)
            }
        }

    }


    private fun setCountryCode(){
        binding.countryCode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                val selectedValue: SpinnerItem = parentView?.getItemAtPosition(position) as SpinnerItem
                binding.chosenCountry.text = selectedValue.countryCode
                binding.phoneNumberEditTxt.hint = selectedValue.hint
                binding.phoneNumberEditTxt.setMask(selectedValue.mask)
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