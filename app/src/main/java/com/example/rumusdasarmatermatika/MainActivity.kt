package com.example.rumusdasarmatermatika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rumusdasarmatermatika.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonPersegi.setOnClickListener {
            val intent = Intent(this,RumusPersegi::class.java)
            startActivity(intent)
        }
        binding.buttonPersegiPanjang.setOnClickListener {
            val intent = Intent(this,RumusPersegiPanjang::class.java)
            startActivity(intent)
        }
        binding.buttonSegitiga.setOnClickListener {
            val intent = Intent(this,RumusSegitiga::class.java)
            startActivity(intent)
        }
        binding.buttonLingkaran.setOnClickListener {
            val intent = Intent(this,RumusLingkaran::class.java)
            startActivity(intent)
        }
    }

}