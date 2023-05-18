package com.mopro.rumusdasarmatermatika.ui

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

    fun persegi(sisi: Long){
        val bangunPersegi = BangunPersegi(sisi)
        val sisi = bangunPersegi.sisi
        countPersegi(sisi)
    }
    fun persegiPanjang(panjang: Long, lebar: Long){
        val bangunPersegiPanjang = BangunPersegiPanjang(panjang, lebar)
        val panjang = bangunPersegiPanjang.panjang
        val lebar = bangunPersegiPanjang.lebar
        countPersegiPanjang(panjang, lebar)
    }
    fun segitiga(alas: Long, tinggi: Long){
        val bangunSegitiga = BangunSegitiga(alas, tinggi)
        val alas = bangunSegitiga.alas
        val tinggi = bangunSegitiga.tinggi
        countSegitiga(alas, tinggi)
    }
    fun lingkaran(jari: Long){
        val bangunLingkaran = BangunLingkaran(jari)
        val jari = bangunLingkaran.jari
        countLingkaran(jari)
    }
    fun countPersegi(sisi: Long){
        val hasil = sisi*sisi
        val dataHasil = HasilEntity(
            bangun = "Persegi",
            input = "Sisi: $sisi",
            hasil_rumus = hasil
        )

        luasPersegi.value = dataHasil.hitungPersegi()
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                db.insert(dataHasil)
            }
        }
    }
    fun countPersegiPanjang(panjang: Long, lebar: Long){
        val hasil = panjang*lebar
        luasPersegiPanjang.value = LuasPersegiPanjang(hasil)
        val dataHasil = HasilEntity(
            bangun = "Persegi Panjang",
            input = "Panjang: $panjang, Lebar: $lebar",
            hasil_rumus = hasil
        )
        luasPersegiPanjang.value = dataHasil.hitungPersegiPanjang()
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                db.insert(dataHasil)
            }
        }
    }
    fun countSegitiga(alas: Long, tinggi: Long){
        val hasil = alas*tinggi*0.5f
        luasSegitiga.value = LuasSegitiga(hasil.toLong())
        val dataHasil = HasilEntity(
            bangun = "Segitiga",
            input = "Alas: $alas, Lebar: $tinggi",
            hasil_rumus = hasil.toLong()
        )
        luasPersegiPanjang.value = dataHasil.hitungPersegiPanjang()
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                db.insert(dataHasil)
            }
        }
    }
    fun countLingkaran(jari: Long){
        val PHI = 3.14
        val hasil = PHI*jari*jari
        val dataHasil = HasilEntity(
            bangun = "Lingkaran",
            input = "Jari-Jari: $jari",
            hasil_rumus = hasil.toLong()
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