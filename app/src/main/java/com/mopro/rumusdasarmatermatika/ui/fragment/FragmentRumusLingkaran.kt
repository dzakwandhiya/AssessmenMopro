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
import com.mopro.rumusdasarmatermatika.databinding.ActivityRumusLingkaranBinding
import com.mopro.rumusdasarmatermatika.db.HasilDb
import com.mopro.rumusdasarmatermatika.model.LuasLingkaran
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainVIewModel
import com.mopro.rumusdasarmatermatika.ui.viewmodel.MainViewModelFactory


class FragmentRumusLingkaran : Fragment() {
    private lateinit var binding: ActivityRumusLingkaranBinding
    fun isDecimal(number: Float):Boolean{
        val decimalPart = number % 1
        return decimalPart == 0f || decimalPart == 0.0f
    }
    //mvvm
    private val viewModel: MainVIewModel by lazy {
        val db = HasilDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainVIewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = ActivityRumusLingkaranBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.hitungLingkaran.setOnClickListener {
            countLingkaran()
        }
        viewModel.getLuasLingkaran().observe(requireActivity(), {showResult(it)})
    }
    private fun countLingkaran(){

        val jari = binding.jariJariInput.text.toString()
        if (TextUtils.isEmpty(jari)) {
            Toast.makeText(context, R.string.jari_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val jariHit = jari.toFloat()
        //MVVM
        viewModel.lingkaran(jariHit)
    }
    //mvvm
    private fun showResult(result: LuasLingkaran?) {
        if (result == null) return
        binding.hasilLingkaranTextView.text = "Hasil : " +
                if(isDecimal(result.hasil)){ result.hasil.toLong()}
                else{ result.hasil}  + " cm²"
    }
}






