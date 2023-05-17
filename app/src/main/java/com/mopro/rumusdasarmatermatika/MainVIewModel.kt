package com.mopro.rumusdasarmatermatika

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mopro.rumusdasarmatermatika.model.*
import java.math.BigInteger

class MainVIewModel : ViewModel() {
    private val luasPersegi = MutableLiveData<LuasPersegi?>()
    private val luasPersegiPanjang = MutableLiveData<LuasPersegiPanjang?>()
    private val luasSegitiga = MutableLiveData<LuasSegitiga?>()
    private val luasLingkaran = MutableLiveData<LuasLingkaran?>()

    fun persegi(sisi: BigInteger){
        val bangunPersegi = BangunPersegi(sisi)
        val sisi = bangunPersegi.sisi
        countPersegi(sisi)
    }
    fun persegiPanjang(panjang: BigInteger, lebar: BigInteger){
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
    fun countPersegi(sisi: BigInteger){
        val hasil = sisi*sisi
        luasPersegi.value = LuasPersegi(hasil)
    }
    fun countPersegiPanjang(panjang: BigInteger, lebar: BigInteger){
        val hasil = panjang*lebar
        luasPersegiPanjang.value = LuasPersegiPanjang(hasil)
    }
    fun countSegitiga(alas: Float, tinggi: Float){
        val hasil = alas*tinggi*0.5f
        luasSegitiga.value = LuasSegitiga(hasil)
    }
    fun countLingkaran(jari: Float){
        val PHI = 3.14
        val hasil = PHI*jari*jari
        luasLingkaran.value = LuasLingkaran(hasil)
    }

    fun getLuasPersegi(): LiveData<LuasPersegi?> = luasPersegi
    fun getLuasPersegiPanjang(): LiveData<LuasPersegiPanjang?> = luasPersegiPanjang
    fun getLuasSegitiga(): LiveData<LuasSegitiga?> = luasSegitiga
    fun getLuasLingkaran(): LiveData<LuasLingkaran?> = luasLingkaran

}