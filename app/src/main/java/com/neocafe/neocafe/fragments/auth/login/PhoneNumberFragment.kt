package com.neocafe.neocafe.fragments.auth.login

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentPhoneNumberBinding
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.models.entities.LoginForm
import com.neocafe.neocafe.utils.Countries
import com.neocafe.neocafe.utils.SpinnerAdapter
import com.neocafe.neocafe.utils.SpinnerItem
import com.neocafe.neocafe.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentPhoneNumberBinding
    private lateinit var spinnerAdapter: SpinnerAdapter
    private val viewModel by viewModel<AuthViewModel>()

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
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.getCodeBtn.setOnClickListener {
            loginRequest()
        }
        loginResponse()
    }

    private fun loginResponse(){
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            if(it is Resource.Success){
                val bundle = Bundle()
                bundle.putString("key", "login")
                findNavController().navigate(R.id.action_phoneNumberFragment_to_otpLoginFragment, bundle)
            }else if(it is Resource.Error){
                binding.errorTxt.visibility = View.VISIBLE
                binding.phoneNumberEditTxt.setTextColor(Color.RED)
                binding.chosenCountry.setTextColor(Color.RED)
                binding.errorTxt.text = "Номер телефона введён неверно, попробуйте еще раз"
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loginRequest(){
        val userNumber = binding.phoneNumberEditTxt.text.toString()
        val maskText = binding.phoneNumberEditTxt.maskString.toString()
        if (viewModel.validPhoneNumber(userNumber.length, maskText.length) == null){
            val loginForm = LoginForm(userNumber)
            viewModel.login(loginForm)
        }else{
            binding.errorTxt.visibility = View.VISIBLE
            binding.phoneNumberEditTxt.setTextColor(Color.RED)
            binding.chosenCountry.setTextColor(Color.RED)
            binding.errorTxt.text = viewModel.validPhoneNumber(userNumber.length, maskText.length)
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