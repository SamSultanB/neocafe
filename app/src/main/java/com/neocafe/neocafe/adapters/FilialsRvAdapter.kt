package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvFilialItemBinding
import com.neocafe.neocafe.entities.branches.Branche

class FilialsRvAdapter: RecyclerView.Adapter<FilialsRvAdapter.ViewHolder>(){

    private var filials: List<Branche> = emptyList()
    var clickToMaps: ((Branche)->Unit)? = null
    var clickToPhone: ((Branche)->Unit)? = null



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = RvFilialItemBinding.bind(itemView)
        fun bind(branche: Branche){
            Glide.with(binding.filialImg).load(branche.image).into(binding.filialImg)
            binding.filialNameTxt.text = branche.name
            binding.addressTxt.text = branche.address
            binding.phoneNumberTxt.text = branche.phone_number
            binding.scheduleTxt.text = branche.work_schedule
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilialsRvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_filial_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilialsRvAdapter.ViewHolder, position: Int) {
        holder.bind(filials[position])
        holder.binding.toMapBtn.setOnClickListener { clickToMaps?.invoke(filials[position]) }
        holder.binding.phoneNumberTxt.setOnClickListener { clickToPhone?.invoke(filials[position])}
    }

    override fun getItemCount(): Int {
        return filials.size
    }

    fun setFilialsList(newList: List<Branche>){
        val diffUtil = FilialsRvDiffUtils(filials, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        this.filials = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}