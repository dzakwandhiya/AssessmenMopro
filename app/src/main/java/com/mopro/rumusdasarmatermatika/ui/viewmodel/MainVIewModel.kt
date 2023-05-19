package com.mopro.rumusdasarmatermatika.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopro.rumusdasarmatermatika.db.HasilDao
import com.mopro.rumusdasarmatermatika.db.HasilEntity
import com.mopro.rumusdasarmatermatika.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainVIewModel(private val db: HasilDao) : ViewModel() {
    private val luasPersegi = MutableLiveData<LuasPersegi?>()
    private val luasPersegiPanjang = MutableLiveData<LuasPersegiPanjang?>()
    private val luasSegitiga = MutableLiveData<LuasSegitiga?>()
    private val luasLingkaran = MutableLiveData<LuasLingkaran?>()

    fun isDecimal(number: Float):Boolean{
        val decimalPart = number % 1
        return decimalPart == 0f || decimalPart == 0.0f
    }

    fun persegi(sisi: Float){
        val bangunPersegi = BangunPersegi(sisi)
        val sisi = bangunPersegi.sisi
        countPersegi(sisi)
    }
    fun persegiPanjang(panjang: Float, lebar: Float){
        val bangunPersegiPanjang = BangunPersegiPanjang(panjang, lebar)
        val panjang = bangunPersegiPanjang.panjang
        val lebar = bangunPersegiPanjang.lebar
        countPersegiPanjang(panjang, lebar)
    }
    fun segitiga(alas: Float, tinggi: Float){
        val bangunSegitiga = BangunSegitiga(alas, tinggi)
        val alas = bangunSegitiga.alas
        val tinggi = bangunSegitiga.tinggi
        countSegitiga(alas, tinggi)
    }
    fun lingkaran(jari: Float){
        val bangunLingkaran = BangunLingkaran(jari)
        val jari = bangunLingkaran.jari
        countLingkaran(jari)
    }
    fun countPersegi(sisi: Float){
        val hasil = sisi*sisi
        if(isDecimal(sisi)){
            sisi
        }else{
            sisi.toLong()
        }
        val dataHasil = HasilEntity(
            bangun = "Persegi",
            input = "Sisi: $sisi cm²",
            hasil_rumus = hasil
        )

        luasPersegi.value = dataHasil.hitungPersegi()
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                db.insert(dataHasil)
            }
        }
    }
    fun countPersegiPanjang(panjang: Float, lebar: Float){
        val hasil = panjang*lebar
        luasPersegiPanjang.value = LuasPersegiPanjang(hasil)
        val dataHasil = HasilEntity(
            bangun = "Persegi Panjang",
            input = "Panjang: $panjang cm², Lebar: $lebar cm²",
            hasil_rumus = hasil
        )
        luasPersegiPanjang.value = dataHasil.hitungPersegiPanjang()
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                db.insert(dataHasil)
            }
        }
    }
    fun countSegitiga(alas: Float, tinggi: Float){
        val hasil = alas*tinggi*0.5f
        luasSegitiga.value = LuasSegitiga(hasil)
        val dataHasil = HasilEntity(
            bangun = "Segitiga",
            input = "Alas: $alas cm², Lebar: $tinggi cm²",
            hasil_rumus = hasil
        )
        luasPersegiPanjang.value = dataHasil.hitungPersegiPanjang()
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                db.insert(dataHasil)
            }
        }
    }
    fun countLingkaran(jari: Float){
        val PHI = 3.14f
        val hasil = PHI*jari*jari
        val dataHasil = HasilEntity(
            bangun = "Lingkaran",
            input = "Jari-Jari: $jari cm²",
            hasil_rumus = hasil
        )

        luasLingkaran.value = dataHasil.hitungLingkaran()
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                db.insert(dataHasil)
            }
        }
    }

    fun getLuasPersegi(): LiveData<LuasPersegi?> = luasPersegi
    fun getLuasPersegiPanjang(): LiveData<LuasPersegiPanjang?> = luasPersegiPanjang
    fun getLuasSegitiga(): LiveData<LuasSegitiga?> = luasSegitiga
    fun getLuasLingkaran(): LiveData<LuasLingkaran?> = luasLingkaran

}