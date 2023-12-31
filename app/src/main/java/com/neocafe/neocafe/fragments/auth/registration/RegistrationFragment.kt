package com.neocafe.neocafe.fragments.auth.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentRegistrationBinding
import com.neocafe.neocafe.entities.registration.RegistrationForm
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.utils.Countries
import com.neocafe.neocafe.utils.SpinnerAdapter
import com.neocafe.neocafe.utils.SpinnerItem
import com.neocafe.neocafe.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var spinnerAdapter: SpinnerAdapter
    private val viewModel by viewModel<AuthViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val birthdate = arguments?.getString("key")
        spinnerCountryCode()
        setCountryCode()
        binding.getCodeBtn.setOnClickListener {
            if (birthdate != null) {
                registrationRequest(birthdate)
            }else{
                registrationRequest()
            }
        }

        registrationResponse()

        binding.arrowBackBtn.setOnClickListener { findNavController().navigateUp() }


    }

    private fun registrationResponse(){
        viewModel.registrationResponse.observe(viewLifecycleOwner, Observer {
            if(it is Resource.Success){
                findNavController().navigate(R.id.action_registrationFragment_to_otpVerificationFragment)
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                println(it.message)
            }
        })
    }

    private fun registrationRequest(birthdate: String? = null){
        val number = binding.phoneNumberEditTxt.text.toString()
        val maskNumber = binding.phoneNumberEditTxt.maskString.toString()
        val userName = binding.userNameEditTxt.text.toString()
        if(viewModel.validPhoneNumber(number.length, maskNumber.length) == null && !userName.isEmpty()){
            val userNumber = (binding.chosenCountry.text.toString()+number).replace(" ".toRegex(), "")
            val registrationForm = RegistrationForm(userName, userNumber, birthdate)
            viewModel.registration(registrationForm)
        }else{
            binding.errorTxt.visibility = View.VISIBLE
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