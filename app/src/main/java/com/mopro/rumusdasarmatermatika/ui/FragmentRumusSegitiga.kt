package com.mopro.rumusdasarmatermatika.ui

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
import com.mopro.rumusdasarmatermatika.model.LuasSegitiga

class FragmentRumusSegitiga : Fragment() {
    private lateinit var binding: ActivityRumusSegitigaBinding
    //mvvm
    private val viewModel: MainVIewModel by lazy {
        ViewModelProvider(this)[MainVIewModel::class.java]
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
        binding.hasilSegitigaTextView.text = "Hasil : " + result.hasil +"cm²"
    }
}





