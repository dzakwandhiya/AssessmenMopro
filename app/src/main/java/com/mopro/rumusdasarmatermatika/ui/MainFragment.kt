package com.mopro.rumusdasarmatermatika.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.mopro.rumusdasarmatermatika.R
import com.mopro.rumusdasarmatermatika.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainVIewModel by lazy {
        ViewModelProvider(requireActivity())[MainVIewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonPersegi.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_mainFragment_to_fragmentRumusPersegi
            )
        }

        binding.buttonPersegiPanjang.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_mainFragment_to_fragmentRumusPersegiPanjang
            )
        }
        binding.buttonSegitiga.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_mainFragment_to_fragmentRumusSegitiga2
            )
        }
        binding.buttonLingkaran.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_mainFragment_to_fragmentRumusLingkaran
            )
        }
    }






}