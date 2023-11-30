package com.neocafe.neocafe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvFilialItemBinding
import com.neocafe.neocafe.utils.Filial

class FilialsRvAdapter: RecyclerView.Adapter<FilialsRvAdapter.ViewHolder>(){

    var filials: List<Filial> = emptyList()
    var clickToMaps: ((Filial)->Unit)? = null
    var clickToPhone: ((Filial)->Unit)? = null



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = RvFilialItemBinding.bind(itemView)
        fun bind(filial: Filial){
            binding.filialImg.setImageResource(filial.image)
            binding.filialNameTxt.text = filial.name
            binding.addressTxt.text = filial.address
            binding.fromTxt.text = "c " + filial.from
            binding.tillTxt.text = "до " + filial.till
            binding.phoneNumberTxt.text = filial.phone
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
}