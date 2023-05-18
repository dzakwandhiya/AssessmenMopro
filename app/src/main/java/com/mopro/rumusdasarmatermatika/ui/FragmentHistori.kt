package com.mopro.rumusdasarmatermatika.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mopro.rumusdasarmatermatika.databinding.HistoriHasilBinding
import com.mopro.rumusdasarmatermatika.db.HasilDb

class FragmentHistori : Fragment() {
    private lateinit var binding: HistoriHasilBinding
    private lateinit var myAdapter: HistoriAdapter
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
        myAdapter = HistoriAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner, {
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })
    }


}