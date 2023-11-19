package com.neocafe.neocafe.fragments.filial

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.FragmentFilialsBinding

class FilialsFragment : Fragment() {

    private lateinit var binding: FragmentFilialsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilialsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notificationsBtn.setOnClickListener {
            callAlertDialog()
        }
    }

    private fun callAlertDialog(){

        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_to_maps)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val goToBtn = dialogScreen.findViewById<Button>(R.id.goToBtn)
        val stayBtn = dialogScreen.findViewById<Button>(R.id.stayBtn)

        goToBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Go to maps", Toast.LENGTH_SHORT).show()
        }
        stayBtn.setOnClickListener {
            dialogScreen.dismiss()
        }
        dialogScreen.show()
    }

}