package com.neocafe.neocafe.fragments.auth.registration

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafe.MainActivity
import com.neocafe.neocafe.databinding.FragmentOtpVerificationBinding
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.entities.login.OTPForm
import com.neocafe.neocafe.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OTPVerificationFragment : Fragment() {

    private lateinit var binding: FragmentOtpVerificationBinding
    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpVerificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arrowBackBtn.setOnClickListener { findNavController().navigateUp() }
        binding.signInBtn.setOnClickListener {
            otpCheckRequest()
        }
        otpResponse()
    }

    private fun otpResponse(){
        viewModel.otpResponse.observe(viewLifecycleOwner, Observer {
            if(it is Resource.Success){
                val intent = Intent(this.activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }else if(it is Resource.Error){
                binding.todoTxt.text = "Код введен неверно, попробуйте еще раз"
                binding.todoTxt.setTextColor(Color.RED)
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun otpCheckRequest(){
        val otpCode = (binding.inputCode1.text.toString() + binding.inputCode2.text.toString() + binding.inputCode3.text.toString() + binding.inputCode4.text.toString()).toInt()
        Toast.makeText(requireContext(), otpCode.toString(), Toast.LENGTH_SHORT).show()
        viewModel.otpCheck(OTPForm(otpCode))
    }

}