package com.rakamin.alodokter.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.utils.ID_DOCTOR
import com.rakamin.alodokter.databinding.ItemListBookingDokterBinding
import com.rakamin.alodokter.domain.model.DoctorModel
import java.util.ArrayList

class ListBookingDokterAdapter: RecyclerView.Adapter<ListBookingDokterAdapter.ViewHolder>() {

    private val listBookingDoctor = ArrayList<DoctorModel>()
    fun setDoctor(doctor: List<DoctorModel>?){
        if (doctor == null) return
        this.listBookingDoctor.clear()
        this.listBookingDoctor.addAll(doctor)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBookingDokterAdapter.ViewHolder {
        val binding = ItemListBookingDokterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = listBookingDoctor[position]
        holder.bind(doctor)
    }

    override fun getItemCount(): Int {
        return listBookingDoctor.size
    }

    inner class ViewHolder(private val binding: ItemListBookingDokterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(doctor: DoctorModel) {
            with(binding) {
                tvNamaDokter.text = doctor.nama
                tvPekerjaanDokter.text = doctor.spesialis
                tvPriceBooking.text = doctor.harga_konsul
                Glide.with(itemView.context)
                    .load(R.drawable.ic_launcher_background)
                    .into(ivDokter)

                itemView.setOnClickListener { view ->
                    val mBundle = Bundle()
                    doctor.id?.let { mBundle.putInt(ID_DOCTOR, it) }
                   // view.findNavController()
                        //.navigate(R.id.action_homeFragment_to_detailFragment, mBundle)
                }
            }
        }
}


}
