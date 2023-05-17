package com.mopro.rumusdasarmatermatika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mopro.rumusdasarmatermatika.databinding.ActivityRumusPersegiPanjangBinding
import com.mopro.rumusdasarmatermatika.model.LuasPersegiPanjang

class RumusPersegiPanjang : AppCompatActivity() {
    private lateinit var binding: ActivityRumusPersegiPanjangBinding
    //mvvm
    private val viewModel: MainVIewModel by lazy {
        ViewModelProvider(this)[MainVIewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRumusPersegiPanjangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hitungPersegiPanjang.setOnClickListener {
            countPersegiPanjang()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //mvvm
        viewModel.getLuasPersegiPanjang().observe(this, {showResult(it)})
    }
    private fun countPersegiPanjang(){
        val panjang = binding.panjangInput.text.toString();
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(this, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val panjangHit = panjang.toBigInteger();
        val lebar = binding.lebarInput.text.toString();
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(this, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
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