package com.mopro.rumusdasarmatermatika.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mopro.rumusdasarmatermatika.databinding.HistoriHasilBinding
import com.mopro.rumusdasarmatermatika.db.HasilDb

class FragmentHistori : Fragment() {
    private lateinit var binding: HistoriHasilBinding
    private val viewModel: HistoriViewModel by lazy {
        val db = HasilDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HistoriHasilBinding.inflate(layoutInflater,
            container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.data.observe(viewLifecycleOwner, {
            Log.d("HistoriFragment", "Jumlah data: ${it.size}")
        })
    }


}