package com.neocafe.neocafe.fragments.auth.login

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
import com.neocafe.neocafe.databinding.FragmentOtpLoginBinding
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.models.entities.OTPForm
import com.neocafe.neocafe.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OTPLoginFragment : Fragment() {

    private lateinit var binding: FragmentOtpLoginBinding
    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = arguments?.getString("key")

        binding.arrowBackBtn.setOnClickListener { findNavController().navigateUp() }
        binding.signInBtn.setOnClickListener {
            val intent = Intent(this.activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
//        otpLoginResponse()
//        otpResponse()
    }


    private fun otpLoginResponse(){
        viewModel.otpLoginResponse.observe(viewLifecycleOwner, Observer{
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

    private fun otpCheckRequest(type: String){
        val otpCode = (binding.inputCode1.text.toString() + binding.inputCode2.text.toString() + binding.inputCode3.text.toString() + binding.inputCode4.text.toString()).toInt()
        if(type == "register"){
            viewModel.otpCheck(OTPForm(otpCode))
        }else if(type == "login"){
            viewModel.otpLoginCheck(OTPForm(otpCode))
        }
    }

}