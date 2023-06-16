package com.mopro.rumusdasarmatermatika.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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
        binding.buttoDaftarRumus.setOnClickListener{
            it.findNavController().navigate(
                R.id.action_mainFragment_to_fragmentIntro
            )
        }
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
                R.id.action_mainFragment_to_fragmentRumusSegitiga
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
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_mainFragment_to_fragmentHistori)
                return true
            }

            R.id.menu_about -> {
                    findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
                    return true
            }
            R.id.bagikan -> {
                shareData()
            }
        }

        return super.onOptionsItemSelected(item)
    }
    private fun shareData() {
        val message = getString(R.string.sharelink)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }







}