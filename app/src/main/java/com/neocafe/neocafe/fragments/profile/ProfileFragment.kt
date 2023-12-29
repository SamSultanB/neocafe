package com.neocafe.neocafe.fragments.profile

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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.LastOrdersRv
import com.neocafe.neocafe.databinding.FragmentProfileBinding
import com.neocafe.neocafe.entities.profile.responses.Profile
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.viewModels.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<ProfileViewModel>()
    private lateinit var profile: Profile
    private val actualAdapter = LastOrdersRv()
    private val lastOrders = LastOrdersRv()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actualOrdersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.lastOrdersRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getProfile()
        getProfileResponse()
        binding.bonusImg.setOnClickListener {
            callAlertDialog()
        }
        binding.editProfileBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("key", profile)
            findNavController().navigate(R.id.action_profileFragment_to_profileEditFragment, bundle)
        }
        binding.notificationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_notificationsFragment)
        }
        binding.logoutBtn.setOnClickListener {
            logoutAlertDialog()
        }

    }

    private fun getProfileResponse(){
        viewModel.getProfileResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                profile = Profile(it.data?.first_name.toString(), it.data?.phone_number.toString(), it.data?.date_of_birth.toString(), it.data?.bonuses.toString(), it.data?.active_orders!!, it.data?.completed_orders!!)
                binding.userNameTxt.text = it.data?.first_name
                actualAdapter.setItems(profile.active_orders)
                lastOrders.setItems(profile.completed_orders)
                binding.actualOrdersRv.adapter = actualAdapter
                binding.lastOrdersRv.adapter = lastOrders
            }else if(it is Resource.Error){
//                println(it.message)
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun callAlertDialog(){
        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_bonus)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val okBtn = dialogScreen.findViewById<Button>(R.id.okBtn)
        okBtn.setOnClickListener {
            dialogScreen.dismiss()
        }
        dialogScreen.show()
    }

    private fun logoutAlertDialog(){
        val dialogScreen = Dialog(requireContext())
        dialogScreen.setContentView(R.layout.alert_dialog_quit)
        dialogScreen.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val yesBtn = dialogScreen.findViewById<Button>(R.id.yesBtn)
        val noBtn = dialogScreen.findViewById<Button>(R.id.noBtn)
        yesBtn.setOnClickListener {
            activity?.finish()
        }
        noBtn.setOnClickListener {
            dialogScreen.dismiss()
        }
        dialogScreen.show()
    }

}