package com.mopro.rumusdasarmatermatika.ui.fragment

import android.os.Bundle
import android.text.TextUtils
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
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModel
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModelFactory

class FragmentRumusPersegi : Fragment() {
    private lateinit var binding: ActivityRumusPersegiBinding
    fun isDecimal(number: Float):Boolean{
        val decimalPart = number % 1
        return decimalPart == 0f || decimalPart == 0.0f
    }
    //mvvm
    private val viewModel: MainViewModel by lazy {
        val db = HasilDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
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
        val sisiNum = sisi.toFloat()

        //mvvm
        viewModel.persegi(sisiNum)
    }
    //mvvm
    private fun showResult(result: LuasPersegi?) {
        if (result == null) return
        binding.hasilPersegiTextView.text = "Hasil : " +
                if(isDecimal(result.hasilLuasPersegi)){ result.hasilLuasPersegi.toLong()}
                else{ result.hasilLuasPersegi}  + " cm²"

    }
}






