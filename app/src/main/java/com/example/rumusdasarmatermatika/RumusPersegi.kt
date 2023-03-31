package com.example.rumusdasarmatermatika


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.rumusdasarmatermatika.databinding.ActivityRumusPersegiBinding

class RumusPersegi : AppCompatActivity() {
    private lateinit var binding: ActivityRumusPersegiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRumusPersegiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hitungPersegi.setOnClickListener {
            countPersegi()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    private fun countPersegi(){
        val sisi = binding.sisiInput.text.toString()
        if (TextUtils.isEmpty(sisi)) {
            Toast.makeText(this, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val sisiNum = sisi.toBigInteger()

        val hasil = sisiNum*sisiNum
        binding.hasilPersegiTextView.text = "Hasil : " + hasil +"cm²"

    }

}






