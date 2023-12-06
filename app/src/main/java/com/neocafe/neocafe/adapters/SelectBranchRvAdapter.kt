package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvChooseFilialItemBinding
import com.neocafe.neocafe.entities.branches.Branche

class SelectBranchRvAdapter: RecyclerView.Adapter<SelectBranchRvAdapter.ViewHolder>() {

    private var branches: List<Branche> = emptyList()
    var chooseClick: ((Branche)->Unit)? = null


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = RvChooseFilialItemBinding.bind(itemView)
        fun bind(branche: Branche){
            binding.branchAddressTxt.text = branche.address
            binding.branchNameTxt.text = branche.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectBranchRvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_choose_filial_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectBranchRvAdapter.ViewHolder, position: Int) {
        holder.bind(branches[position])
        holder.binding.branchNameTxt.setOnClickListener { chooseClick?.invoke(branches[position]) }
    }

    override fun getItemCount(): Int {
        return branches.size
    }

    fun setList(newList: List<Branche>){
        branches = newList
    }
}