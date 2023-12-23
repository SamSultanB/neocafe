package com.neocafe.neocafe.fragments.profile

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentProfileEditBinding
import com.neocafe.neocafe.entities.profile.requests.ProfileRequest
import com.neocafe.neocafe.entities.profile.responses.Profile
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.utils.Countries
import com.neocafe.neocafe.utils.SpinnerAdapter
import com.neocafe.neocafe.utils.SpinnerItem
import com.neocafe.neocafe.viewModels.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ProfileEditFragment : Fragment() {

    private lateinit var binding: FragmentProfileEditBinding
    private lateinit var spinnerAdapter: SpinnerAdapter
    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profile = arguments?.getSerializable("key") as Profile
        binding.userNameEditTxt.setText(profile.first_name)
        binding.chosenCountry.text = profile.phone_number.substring(0, 2)
        binding.phoneNumberEditTxt.setText(setPhoneNumber(profile.phone_number))
        if(profile.date_of_birth != "null"){
            binding.birthdateEditTxt.setText(convertDateFormatFrom(profile.date_of_birth))
        }

        setCountryCode()
        spinnerCountryCode()
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.toNotificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileEditFragment_to_notificationsFragment)
        }

        binding.saveBtn.setOnClickListener {
            updateProfile()
        }
        updateProfileResponse()

    }

    private fun setPhoneNumber(number: String): String{
        if(number.contains("+996")){
            return number.removeRange(0, 4)
        }else if(number.contains("+7") || number.contains("+1")){
            return number.removeRange(0, 2)
        }else if(number.contains("+49")){
            return number.removeRange(0,2)
        }
        return number
    }

    private fun updateProfileResponse(){
        viewModel.setProfileResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                callAlertDialog()
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun updateProfile(){
        val name = binding.userNameEditTxt.text.toString()
        val birthdate = convertDateFormatTo(binding.birthdateEditTxt.text.toString())
        val countryCode = binding.chosenCountry.text
        val phoneNumber = binding.phoneNumberEditTxt.text
        val userNumber = (countryCode.toString()+phoneNumber).replace(" ".toRegex(), "")
        val profile = ProfileRequest(name, userNumber, birthdate)
        viewModel.setProfile(profile)
    }


    private fun callAlertDialog(){

        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_profil_edit)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val yesBtn = dialogScreen.findViewById<Button>(R.id.yesBtn)
        val noBtn = dialogScreen.findViewById<Button>(R.id.noBtn)

        noBtn.setOnClickListener {
            dialogScreen.dismiss()
        }
        yesBtn.setOnClickListener {
            dialogScreen.dismiss()
            findNavController().navigateUp()
        }
        dialogScreen.show()

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
        binding.countryCode.setEnabled(true)
    }

    fun convertDateFormatTo(originalDate: String): String {
        val originalFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val parsedDate = LocalDate.parse(originalDate, originalFormatter)
        val desiredFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return parsedDate.format(desiredFormatter)
    }

    fun convertDateFormatFrom(originalDate: String): String {
        val originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val parsedDate = LocalDate.parse(originalDate, originalFormatter)
        val desiredFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return parsedDate.format(desiredFormatter)
    }


}