package com.mopro.rumusdasarmatermatika.ui.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mopro.rumusdasarmatermatika.MainActivity
import com.mopro.rumusdasarmatermatika.R
import com.mopro.rumusdasarmatermatika.databinding.MainFragmentBinding
import com.mopro.rumusdasarmatermatika.db.HasilDb
import com.mopro.rumusdasarmatermatika.network.ApiStatus
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModel
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModelFactory


class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {

            }
            ApiStatus.SUCCESS -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestNotificationPermission()
                }
            }
            ApiStatus.FAILED -> {

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    private val viewModel: MainViewModel by lazy {
        val db = HasilDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.scheduleUpdater(requireActivity().application)
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
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }






}