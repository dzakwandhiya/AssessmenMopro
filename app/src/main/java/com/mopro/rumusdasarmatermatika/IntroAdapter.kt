package com.mopro.rumusdasarmatermatika

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mopro.rumusdasarmatermatika.databinding.ItemRumusBinding
import com.mopro.rumusdasarmatermatika.model.Intro
import com.mopro.rumusdasarmatermatika.network.IntroApi

class IntroAdapter : ListAdapter<Intro, IntroAdapter.IntroViewHolder>(IntroDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRumusBinding.inflate(inflater, parent, false)
        return IntroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class IntroViewHolder(private val binding: ItemRumusBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(intro: Intro) {
            binding.namaBangunTextView.text = "Bangun: ${intro.namaBangun}"
            binding.jenisRumus.text = "Rumus: ${intro.jenisRumus}"
            Glide.with(binding.bangun.context)
                .load(IntroApi.getImage(intro.imageId))
                .error(R.drawable.baseline_broken_image)
                .into(binding.bangun)
            // Bind other views with data from intro object
        }
    }

    private class IntroDiffCallback : DiffUtil.ItemCallback<Intro>() {
        override fun areItemsTheSame(oldItem: Intro, newItem: Intro): Boolean {
            return oldItem.imageId == newItem.imageId
        }

        override fun areContentsTheSame(oldItem: Intro, newItem: Intro): Boolean {
            return oldItem == newItem
        }
    }
}
