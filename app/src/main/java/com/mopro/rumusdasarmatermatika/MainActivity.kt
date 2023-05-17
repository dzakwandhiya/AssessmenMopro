package com.mopro.rumusdasarmatermatika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    //tombol up/back
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //tombol up/back
        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
    //tombol up/back
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.buttonPersegi.setOnClickListener {
//            val intent = Intent(this,RumusPersegi::class.java)
//            startActivity(intent)
//        }
//        binding.buttonPersegiPanjang.setOnClickListener {
//            val intent = Intent(this,RumusPersegiPanjang::class.java)
//            startActivity(intent)
//        }
//        binding.buttonSegitiga.setOnClickListener {
//            val intent = Intent(this,RumusSegitiga::class.java)
//            startActivity(intent)
//        }
//        binding.buttonLingkaran.setOnClickListener {
//            val intent = Intent(this,RumusLingkaran::class.java)
//            startActivity(intent)
//        }
//    }

}