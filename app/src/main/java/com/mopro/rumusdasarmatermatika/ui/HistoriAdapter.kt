package com.mopro.rumusdasarmatermatika.ui

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mopro.rumusdasarmatermatika.R
import com.mopro.rumusdasarmatermatika.databinding.ItemHistoriBinding
import com.mopro.rumusdasarmatermatika.db.HasilEntity
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
        //jika angka(float) tersebut bisa jadi angka biasa(tidak ada 0 di belakang koma)
        fun isDecimal(number: Float):Boolean{
            val decimalPart = number % 1
            return decimalPart == 0f || decimalPart == 0.0f
        }
        fun bind(item: HasilEntity) = with(binding) {

                //bangunTextView.setImageResource(R.drawable.square)
                typeTextView.text = item.bangun
                val hasil = item.hasil_rumus
                val input = item.input
                if(isDecimal(hasil)){
                    dataTextView.text = "$input | Hasil: ${hasil.toLong()} cm²"
                }else{
                    dataTextView.text = "$input | Hasil: $hasil cm²"
                }
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
