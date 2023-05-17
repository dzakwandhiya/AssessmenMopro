package com.mopro.rumusdasarmatermatika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mopro.rumusdasarmatermatika.databinding.ActivityRumusPersegiBinding
import com.mopro.rumusdasarmatermatika.model.LuasPersegi

class RumusPersegi : AppCompatActivity() {
    private lateinit var binding: ActivityRumusPersegiBinding
    //mvvm
    private val viewModel: MainVIewModel by lazy {
        ViewModelProvider(this)[MainVIewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRumusPersegiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hitungPersegi.setOnClickListener {
            countPersegi()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //mvvm
        viewModel.getLuasPersegi().observe(this, {showResult(it)})
    }
    private fun countPersegi(){
        val sisi = binding.sisiInput.text.toString()
        if (TextUtils.isEmpty(sisi)) {
            Toast.makeText(this, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
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






