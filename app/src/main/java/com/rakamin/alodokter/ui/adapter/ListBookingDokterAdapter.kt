package com.rakamin.alodokter.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakamin.alodokter.R
import com.rakamin.alodokter.core.utils.ID_DOCTOR
import com.rakamin.alodokter.databinding.ItemListBookingDokterBinding
import com.rakamin.alodokter.domain.model.ListDoctorModel
import java.util.ArrayList

class ListBookingDokterAdapter: RecyclerView.Adapter<ListBookingDokterAdapter.ViewHolder>() {

    private val listBookingDoctor = ArrayList<ListDoctorModel>()

    fun setDoctor(listDoctor: List<ListDoctorModel>?){
        if (listDoctor == null) return
        this.listBookingDoctor.clear()
        this.listBookingDoctor.addAll(listDoctor)
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

    inner class ViewHolder(private val binding: ItemListBookingDokterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listDoctor: ListDoctorModel) {
            with(binding) {
                tvDoctorName.text = listDoctor.nama
                tvDoctorSpecialist.text = listDoctor.spesialis
                tvPriceBooking.text = listDoctor.hargaKonsul.toString()
                Glide.with(itemView.context)
                    .load(R.drawable.ic_launcher_background)
                    .into(ivDoctor)

                itemView.setOnClickListener { view ->
                    val mBundle = Bundle()
                    listDoctor.id?.let { mBundle.putInt(ID_DOCTOR, it) }
                   // view.findNavController()
                        //.navigate(R.id.action_homeFragment_to_detailFragment, mBundle)
                }
            }
        }
}


}
