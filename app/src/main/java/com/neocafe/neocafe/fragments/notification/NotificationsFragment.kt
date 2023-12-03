package com.neocafe.neocafe.fragments.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.adapters.NotificatioinsRvAdapter
import com.neocafe.neocafe.databinding.FragmentNotificationsBinding
import com.neocafe.neocafe.utils.Notification
import com.neocafe.neocafe.utils.SwipeGesture


class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView?.visibility = View.GONE

        val adapter = NotificatioinsRvAdapter()


        val swipeGesture = object: SwipeGesture(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT -> {
                        adapter.delete(viewHolder.adapterPosition)
                    }
                    ItemTouchHelper.RIGHT -> {
                        adapter.delete(viewHolder.adapterPosition)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.notificationsRv)

        //test
        binding.notificationsRv.layoutManager = LinearLayoutManager(requireContext())
        adapter.setList(listOf(Notification("Ваш заказ готов", "Капучино 2x, Американо3x, Багровый 1x", "20:02"),
            Notification("Ваш заказ готов", "Капучино 2x, Американо3x, Багровый 1x", "20:02")))
        binding.notificationsRv.adapter = adapter
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
            bottomNavigationView?.visibility = View.VISIBLE
        }
    }

}