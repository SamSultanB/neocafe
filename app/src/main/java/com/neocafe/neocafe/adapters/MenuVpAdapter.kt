package com.neocafe.neocafe.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neocafe.neocafe.entities.categories.Category
import com.neocafe.neocafe.fragments.main.CategoryListFragment

class MenuVpAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle){

    var items: List<Category> = emptyList()
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putSerializable("key", items[position])
        val fragment = CategoryListFragment()
        fragment.arguments = bundle
        return fragment
    }

    fun setData(new: List<Category>){
        items = new
    }

}