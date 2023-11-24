package com.neocafe.neocafe.fragments.filial

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.FilialsRvAdapter
import com.neocafe.neocafe.databinding.FragmentFilialsBinding
import com.neocafe.neocafe.utils.Filial

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

        //test
        val adapter = FilialsRvAdapter()
        adapter.filials = listOf(
            Filial(R.drawable.filial_test1, "Кофейня NeoCafe #1", "Ибраимова, 113", "11:00", "23:00", "fjfdj", "0999 343 321"),
            Filial(R.drawable.filial_test2, "Кофейня NeoCafe #2", "Советская, 10", "10:00", "21:00", "fjfdj", "0999 343 033")

        )
        binding.filialsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.filialsRv.adapter = adapter
        adapter.clickToMaps = {
            callAlertDialog()
        }
        adapter.clickToPhone = {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("0999 343 321"))
            startActivity(intent)
        }


        binding.notificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_filialsFragment_to_notificationsFragment)
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