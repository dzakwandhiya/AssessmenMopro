package com.mopro.rumusdasarmatermatika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.mopro.rumusdasarmatermatika.databinding.ActivityRumusLingkaranBinding

class RumusLingkaran : AppCompatActivity() {
    private lateinit var binding: ActivityRumusLingkaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumus_lingkaran)
        binding = ActivityRumusLingkaranBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hitungLingkaran.setOnClickListener {
            countLingkaran()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    private fun countLingkaran(){
        val PHI = 3.14
        val jari = binding.jariJariInput.text.toString()
        if (TextUtils.isEmpty(jari)) {
            Toast.makeText(this, R.string.jari_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val jariHit = jari.toFloat()
        binding.hasilLingkaranTextView.text = "Hasil : " + PHI*jariHit*jariHit + "cm²"
    }
}