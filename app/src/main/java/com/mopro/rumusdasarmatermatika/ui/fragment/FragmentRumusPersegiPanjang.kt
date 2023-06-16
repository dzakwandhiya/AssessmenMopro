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
import com.mopro.rumusdasarmatermatika.databinding.ActivityRumusPersegiPanjangBinding
import com.mopro.rumusdasarmatermatika.db.HasilDb
import com.mopro.rumusdasarmatermatika.model.LuasPersegiPanjang
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModel
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModelFactory

class FragmentRumusPersegiPanjang : Fragment() {
    private lateinit var binding: ActivityRumusPersegiPanjangBinding
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
        binding = ActivityRumusPersegiPanjangBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.hitungPersegiPanjang.setOnClickListener {
            countPersegiPanjang()
        }
        viewModel.getLuasPersegiPanjang().observe(requireActivity(), {showResult(it)})
    }
    private fun countPersegiPanjang(){
        val panjang = binding.panjangInput.text.toString();
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val panjangHit = panjang.toFloat();
        val lebar = binding.lebarInput.text.toString();
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val lebarHit = lebar.toFloat();
        //mvvm
        viewModel.persegiPanjang(panjangHit, lebarHit)
    }
    //mvvm
    private fun showResult(result: LuasPersegiPanjang?) {
        if (result == null) return
        binding.hasilPersegiPanjangTextView.text = "Hasil : " +
                if(isDecimal(result.hasilPersegiPanjang)){ result.hasilPersegiPanjang.toLong()}
                else{ result.hasilPersegiPanjang}  + " cm²"
    }
}






