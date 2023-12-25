package com.neocafe.neocafe.adapters

import android.graphics.Color
import android.graphics.LightingColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neocafe.neocafe.R
import com.neocafe.neocafe.databinding.RvFilialItemBinding
import com.neocafe.neocafe.entities.branches.Branche
import java.time.DayOfWeek
import java.time.LocalDate

class FilialsRvAdapter: RecyclerView.Adapter<FilialsRvAdapter.ViewHolder>(){

    private var filials: List<Branche> = emptyList()
    var clickToMaps: ((Branche)->Unit)? = null
    var clickToPhone: ((Branche)->Unit)? = null



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = RvFilialItemBinding.bind(itemView)
        val currentDate = LocalDate.now()
        // Get the day of the week as an enum (DayOfWeek)
        val dayOfWeekEnum: DayOfWeek = currentDate.dayOfWeek
        fun bind(branche: Branche){
            val colorFilter = LightingColorFilter(Color.rgb(153, 143, 166), 0)
            binding.filialImg.colorFilter = colorFilter
            Glide.with(binding.filialImg).load(branche.image).into(binding.filialImg)
            binding.filialNameTxt.text = branche.name
            binding.addressTxt.text = branche.address
            binding.phoneNumberTxt.text = branche.phone_number
            when(dayOfWeekEnum){
                DayOfWeek.MONDAY -> binding.scheduleTxt.text = "c " + branche.monday_start_time.substring(0, 5) + " до " + branche.monday_end_time.substring(0, 5)
                DayOfWeek.TUESDAY -> binding.scheduleTxt.text = "c " + branche.tuesday_start_time.substring(0, 5) + " до " + branche.tuesday_end_time.substring(0, 5)
                DayOfWeek.WEDNESDAY -> binding.scheduleTxt.text = "c " + branche.wednesday_start_time.substring(0, 5) + " до " + branche.wednesday_end_time.substring(0, 5)
                DayOfWeek.THURSDAY -> binding.scheduleTxt.text = "c " + branche.thursday_start_time.substring(0, 5) + " до " + branche.thursday_end_time.substring(0, 5)
                DayOfWeek.FRIDAY -> binding.scheduleTxt.text = "c " + branche.friday_start_time.substring(0, 5) + " до " + branche.friday_end_time.substring(0, 5)
                DayOfWeek.SATURDAY -> binding.scheduleTxt.text = "c " + branche.saturday_start_time.substring(0, 5) + " до " + branche.saturday_end_time.substring(0, 5)
                DayOfWeek.SUNDAY -> binding.scheduleTxt.text = "c " + branche.sunday_start_time.substring(0, 5) + " до " + branche.sunday_end_time.substring(0, 5)
            }
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