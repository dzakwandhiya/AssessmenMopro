package com.mopro.rumusdasarmatermatika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mopro.rumusdasarmatermatika.databinding.ActivityRumusLingkaranBinding
import com.mopro.rumusdasarmatermatika.model.LuasLingkaran
import com.mopro.rumusdasarmatermatika.model.LuasPersegiPanjang

class RumusLingkaran : AppCompatActivity() {
    private lateinit var binding: ActivityRumusLingkaranBinding
    //mvvm
    private val vIewModel: MainVIewModel by lazy {
        ViewModelProvider(this)[MainVIewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumus_lingkaran)
        binding = ActivityRumusLingkaranBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hitungLingkaran.setOnClickListener {
            countLingkaran()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //mvvm
        vIewModel.getLuasLingkaran().observe(this, {showResult(it)})
    }
    private fun countLingkaran(){

        val jari = binding.jariJariInput.text.toString()
        if (TextUtils.isEmpty(jari)) {
            Toast.makeText(this, R.string.jari_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val jariHit = jari.toFloat()
        //MVVM
        vIewModel.countLingkarn(jariHit)
    }
    //mvvm
    private fun showResult(result: LuasLingkaran?) {
        if (result == null) return
        binding.hasilLingkaranTextView.text = "Hasil : " + result.hasil +"cm²"
    }
}
