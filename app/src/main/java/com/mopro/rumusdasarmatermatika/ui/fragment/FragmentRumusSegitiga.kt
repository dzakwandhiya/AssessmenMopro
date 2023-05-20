package com.mopro.rumusdasarmatermatika.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mopro.rumusdasarmatermatika.R
import com.mopro.rumusdasarmatermatika.databinding.ActivityRumusSegitigaBinding
import com.mopro.rumusdasarmatermatika.db.HasilDb
import com.mopro.rumusdasarmatermatika.model.LuasSegitiga
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModel
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModelFactory

class FragmentRumusSegitiga : Fragment() {
    private lateinit var binding: ActivityRumusSegitigaBinding
    fun isDecimal(number: Float):Boolean{
        val decimalPart = number % 1
        return decimalPart == 0f || decimalPart == 0.0f
    }
    //mvvm
    private val viewModel: MainViewModel by lazy {
        val db = HasilDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = ActivityRumusSegitigaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.hitungSegitiga.setOnClickListener {
            countSegitiga()
        }
        viewModel.getLuasSegitiga().observe(requireActivity(), {showResult(it)})
    }
    private fun countSegitiga(){
        val alas = binding.alasInput.text.toString()
        if (TextUtils.isEmpty(alas)) {
            Toast.makeText(context, R.string.alas_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val alasHit = alas.toFloat();

        val tinggi = binding.tinggiInput.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val tinggiHit = tinggi.toFloat();
        //mvvm
        viewModel.segitiga(alasHit, tinggiHit)


    }
    //mvvm
    private fun showResult(result: LuasSegitiga?) {
        if (result == null) return
        binding.hasilSegitigaTextView.text = "Hasil : " +
                if(isDecimal(result.hasilLuasSegitiga)){ result.hasilLuasSegitiga.toLong()}
                else{ result.hasilLuasSegitiga}  + " cm²"
    }
}






