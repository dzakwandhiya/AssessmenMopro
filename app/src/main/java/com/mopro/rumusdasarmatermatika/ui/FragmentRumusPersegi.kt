package com.mopro.rumusdasarmatermatika.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mopro.rumusdasarmatermatika.R
import com.mopro.rumusdasarmatermatika.databinding.ActivityRumusPersegiBinding
import com.mopro.rumusdasarmatermatika.db.HasilDb
import com.mopro.rumusdasarmatermatika.model.LuasPersegi

class FragmentRumusPersegi : Fragment() {
    private lateinit var binding: ActivityRumusPersegiBinding
    //mvvm
    private val viewModel: MainVIewModel by lazy {
        val db = HasilDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainVIewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = ActivityRumusPersegiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.hitungPersegi.setOnClickListener {
            countPersegi()
        }
        viewModel.getLuasPersegi().observe(requireActivity(), {showResult(it)})

    }
    private fun countPersegi(){
        val sisi = binding.sisiInput.text.toString()
        if (TextUtils.isEmpty(sisi)) {
            Toast.makeText(context, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val sisiNum = sisi.toBigInteger()

        //mvvm
        viewModel.persegi(sisiNum)
    }
    //mvvm
    private fun showResult(result: LuasPersegi?) {
        if (result == null) return
        binding.hasilPersegiTextView.text = "Hasil : " + result.hasil +"cm²"
    }
}






