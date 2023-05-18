package com.mopro.rumusdasarmatermatika.ui


import android.graphics.drawable.GradientDrawable
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mopro.rumusdasarmatermatika.R
import com.mopro.rumusdasarmatermatika.databinding.ItemHistoriBinding
import com.mopro.rumusdasarmatermatika.db.HasilEntity
import com.mopro.rumusdasarmatermatika.model.hitungPersegi
import java.util.*

class HistoriAdapter :
    ListAdapter<HasilEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<HasilEntity>() {
                override fun areItemsTheSame(
                    oldData: HasilEntity, newData: HasilEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: HasilEntity, newData: HasilEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        fun bind(item: HasilEntity) = with(binding) {

                //bangunTextView.setImageResource(R.drawable.square)
                typeTextView.text = item.bangun
                val hasil = item.hasil_rumus
                dataTextView.text = "${item.input} | Hasil: $hasil"
                tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
                if(item.bangun.equals("Persegi")){
                    bangunTextView.setImageResource(R.drawable.square)
                }
                else if(item.bangun.equals("Persegi Panjang")){
                    bangunTextView.setImageResource(R.drawable.rectangle)
                }
                else if(item.bangun.equals("Segitiga")){
                    bangunTextView.setImageResource(R.drawable.triangle)
                }
                else{
                    bangunTextView.setImageResource(R.drawable.circle)
                }

                //bangunTextView.setImageResource(R.drawable.square)

//                val bangun = item.bangun
//
//                    val input = item.input
//                typeTextView.text = bangun
//                val hasil = item.hitungPersegi()
//                dataTextView.text = "Sisi = $input"
//                tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
//                bangunTextView.setImageResource(R.drawable.square)


//            val bangun = item.bangun
//            val input = item.input
//            typeTextView.text = bangun
//            val hasil = item.hitungPersegi()
//            dataTextView.text = "Sisi = $input"
//            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
//            if(item.bangun.equals("Persegi")){
//                bangunTextView.setImageResource(R.drawable.square)
//            }

//            val colorRes = when(item.bangun) {
//                bangun.equals("persegi") -> {
//                    R.color.white
//                }
//
//                else -> {
//                    R.color.teal_200
//                }
//            }
//            val circleBg = bangunTextView.background as GradientDrawable
//            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))


//            if(item.bangun.equals("persegi")){
//                val circleBg = bangunTextView.background as GradientDrawable
//                circleBg.setColor(ContextCompat.getColor(root.context, --))
//            }

        //            val circleBg = hasilTextView.background as GradientDrawable
//            circleBg.setColor(ContextCompat.getColor(root.context, R.color.persegi))
//            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
//            bangunTextView.text = root.context.getString(R.string.nama_bangun,
//                hasilBmi.bmi, hasilBmi.kategori.toString())
//            val gender = root.context.getString(
//                if (item.isMale) R.string.pria else R.string.wanita)
//            dataTextView.text = root.context.getString(R.string.data_x,
//                item.berat, item.tinggi, gender)

//            typeTextView.text = hasilPersegi.hasil.toString().substring(0, 1)
//            val circleBg = hasilTextView.background as GradientDrawable
//            circleBg.setColor(ContextCompat.getColor(root.context, R.color.persegi))
//            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
//            bangunTextView.text = root.context.getString(R.string.nama_bangun,
//                hasilBmi.bmi, hasilBmi.kategori.toString())
//            val gender = root.context.getString(
//                if (item.isMale) R.string.pria else R.string.wanita)
//            dataTextView.text = root.context.getString(R.string.data_x,
//                item.berat, item.tinggi, gender)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
