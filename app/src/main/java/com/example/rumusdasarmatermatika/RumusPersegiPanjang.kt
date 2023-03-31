package com.example.rumusdasarmatermatika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.rumusdasarmatermatika.databinding.ActivityRumusPersegiPanjangBinding

class RumusPersegiPanjang : AppCompatActivity() {
    private lateinit var binding: ActivityRumusPersegiPanjangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRumusPersegiPanjangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hitungPersegiPanjang.setOnClickListener {
            countPersegiPanjang()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
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
        val hasil = panjangHit*lebarHit
        binding.hasilPersegiPanjangTextView.text = "Hasil : " + hasil +"cm²"


    }
}