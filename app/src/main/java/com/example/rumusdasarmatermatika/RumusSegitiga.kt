package com.example.rumusdasarmatermatika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.rumusdasarmatermatika.databinding.ActivityRumusSegitigaBinding

class RumusSegitiga : AppCompatActivity() {
    private lateinit var binding: ActivityRumusSegitigaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumus_segitiga)
        binding = ActivityRumusSegitigaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hitungSegitiga.setOnClickListener {
            countSegitiga()
        }
    }
    private fun countSegitiga(){
        val alas = binding.alasInput.text.toString()
        if (TextUtils.isEmpty(alas)) {
            Toast.makeText(this, R.string.alas_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val alasHit = alas.toFloat();

        val tinggi = binding.tinggiInput.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(this, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tinggiHit = alas.toFloat();
        binding.hasilPersegiPanjangTextView.text = "Hasil : " + (tinggiHit*alasHit)/2
    }
}