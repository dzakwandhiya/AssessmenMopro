package com.mopro.rumusdasarmatermatika.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mopro.rumusdasarmatermatika.R
import com.mopro.rumusdasarmatermatika.databinding.MainFragmentBinding


class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_mainFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }






}