package com.mopro.rumusdasarmatermatika.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mopro.rumusdasarmatermatika.IntroAdapter
import com.mopro.rumusdasarmatermatika.databinding.RumusFragmentBinding
import com.mopro.rumusdasarmatermatika.db.HasilDb
import com.mopro.rumusdasarmatermatika.network.ApiStatus
import com.mopro.rumusdasarmatermatika.network.IntroApi
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModel
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class FragmentIntro : Fragment() {
    private lateinit var binding: RumusFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IntroAdapter
    private lateinit var emptyView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RumusFragmentBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView

        return binding.root
    }
    private val viewModel: MainViewModel by lazy {
        val db = HasilDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = IntroAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fetchData()
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
    }
    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
                binding.connected.visibility = View.VISIBLE
            }
        }
    }

    private fun fetchData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = IntroApi.service.getApiData()
                if (response.isNotEmpty()) {
                    adapter.submitList(response)
                    recyclerView.visibility = View.VISIBLE
                    emptyView.visibility = View.GONE
                } else {
                    recyclerView.visibility = View.GONE
                    emptyView.visibility = View.VISIBLE
                }
            } catch (e: HttpException) {
                // Handle HttpException here
            } catch (e: Throwable) {
                // Handle other exceptions here
            }
        }
    }
}
