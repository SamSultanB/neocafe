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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.FilialsRvAdapter
import com.neocafe.neocafe.databinding.FragmentFilialsBinding
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.viewModels.BranchesViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilialsFragment : Fragment() {

    private lateinit var binding: FragmentFilialsBinding
    private val viewModel by viewModel<BranchesViewModel>()
    private val filialsAdapter by inject<FilialsRvAdapter>()

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
        binding.filialsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.filialsRv.adapter = filialsAdapter

        viewModel.getAllBranches()
        getAllFilialsResponse()

        filialsAdapter.clickToMaps = { callAlertDialogToMaps() }
        filialsAdapter.clickToPhone = { callAction(it.phone_number) }

        binding.notificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_filialsFragment_to_notificationsFragment)
        }
    }

    private fun getAllFilialsResponse(){
        viewModel.getAllBranchesResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                it.data?.let { filialsAdapter.setFilialsList(it) }
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun callAlertDialogToMaps(){

        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_to_maps)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val goToBtn = dialogScreen.findViewById<Button>(R.id.goToBtn)
        val stayBtn = dialogScreen.findViewById<Button>(R.id.stayBtn)

        goToBtn.setOnClickListener {
            val mapsIntent = Intent(Intent.ACTION_VIEW)
            startActivity(mapsIntent)
        }
        stayBtn.setOnClickListener {
            dialogScreen.dismiss()
        }
        dialogScreen.show()
    }

    private fun callAction(number: String){
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(dialIntent)
    }

}