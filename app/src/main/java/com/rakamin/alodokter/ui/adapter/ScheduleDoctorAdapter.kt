package com.rakamin.alodokter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rakamin.alodokter.core.data.source.remote.response.ScheduleItem
import com.rakamin.alodokter.core.utils.Helper
import com.rakamin.alodokter.databinding.ItemScheduleDoctorBinding

class ScheduleDoctorAdapter : RecyclerView.Adapter<ScheduleDoctorAdapter.ScheduleDoctorViewHolder>() {

    private val listScheduleDoctor = ArrayList<ScheduleItem>()

    fun setSchedule(schedule : List<ScheduleItem>?) {
        if (schedule == null) return
        listScheduleDoctor.clear()
        listScheduleDoctor.addAll(schedule)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDoctorAdapter.ScheduleDoctorViewHolder {
        val binding = ItemScheduleDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleDoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleDoctorAdapter.ScheduleDoctorViewHolder, position: Int) {
        val schedule = listScheduleDoctor[position]
        holder.bind(schedule)
    }

    override fun getItemCount(): Int = listScheduleDoctor.size

    inner class ScheduleDoctorViewHolder(private val binding: ItemScheduleDoctorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(schedule : ScheduleItem) {
            with(binding) {
                tvDate.text = schedule.hari?.let { schedule.tanggal?.let { it1 -> Helper.dateToDDMMYYYWithDay(
                    it, it1
                ) } }
                val timeDoctorAdapter = TimeDoctorAdapter()
                timeDoctorAdapter.setTime(schedule.docTime)
                binding.rvTime.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)
                binding.rvTime.adapter = timeDoctorAdapter
            }
        }
    }
}