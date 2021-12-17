package com.rakamin.alodokter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rakamin.alodokter.core.data.source.remote.response.DoctorTimeItem
import com.rakamin.alodokter.databinding.ItemTimeDoctorBinding

class TimeDoctorAdapter : RecyclerView.Adapter<TimeDoctorAdapter.TimeDoctorViewHolder>() {

    private val listTimeDoctor = ArrayList<DoctorTimeItem>()

    fun setTime(time : List<DoctorTimeItem>?) {
        if (time == null) return
        listTimeDoctor.clear()
        listTimeDoctor.addAll(time)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeDoctorAdapter.TimeDoctorViewHolder {
        val binding = ItemTimeDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeDoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeDoctorAdapter.TimeDoctorViewHolder, position: Int) {
        val time = listTimeDoctor[position]
        holder.bind(time)
    }

    override fun getItemCount(): Int = listTimeDoctor.size

    inner class TimeDoctorViewHolder(private val binding: ItemTimeDoctorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(time : DoctorTimeItem) {
            with(binding) {
                tvTime.text = time.jam
            }
        }
    }
}