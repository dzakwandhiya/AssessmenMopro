package com.mopro.rumusdasarmatermatika.ui

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
import com.mopro.rumusdasarmatermatika.model.LuasPersegiPanjang

class FragmentRumusPersegiPanjang : Fragment() {
    private lateinit var binding: ActivityRumusPersegiPanjangBinding
    //mvvm
    private val viewModel: MainVIewModel by lazy {
        ViewModelProvider(this)[MainVIewModel::class.java]
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
        val panjangHit = panjang.toBigInteger();
        val lebar = binding.lebarInput.text.toString();
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val lebarHit = lebar.toBigInteger();
        //mvvm
        viewModel.persegiPanjang(panjangHit, lebarHit)
    }
    //mvvm
    private fun showResult(result: LuasPersegiPanjang?) {
        if (result == null) return
        binding.hasilPersegiPanjangTextView.text = "Hasil : " + result.hasil +"cm²"
    }
}






