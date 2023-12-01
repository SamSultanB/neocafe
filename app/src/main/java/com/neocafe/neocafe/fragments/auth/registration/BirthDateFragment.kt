package com.neocafe.neocafe.fragments.auth.registration

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentBirthDateBinding
import com.neocafe.neocafe.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class BirthDateFragment : Fragment() {

    private lateinit var binding: FragmentBirthDateBinding
    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBirthDateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continueBtn.setOnClickListener {
            birthdate()
        }
        binding.arrowBackBtn.setOnClickListener { findNavController().navigateUp() }
    }

    private fun birthdate(){
        val birthdate = binding.birthdateEditTxt.text.toString()
        if(!isValidDate(birthdate) && !birthdate.isEmpty()){
            binding.instructionTxt.text = "Неправильная дата рождения"
            binding.instructionTxt.setTextColor(Color.RED)
        }else{
            findNavController().navigate(R.id.action_birthDateFragment_to_registrationFragment)
        }
    }

    private fun isValidDate(date: String): Boolean {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return try {
            dateFormat.isLenient = false
            dateFormat.parse(date)
            true
        } catch (e: ParseException) {
            false
        }
    }

}