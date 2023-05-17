package com.mopro.rumusdasarmatermatika

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mopro.rumusdasarmatermatika.model.LuasPersegi
import com.mopro.rumusdasarmatermatika.model.LuasPersegiPanjang
import com.mopro.rumusdasarmatermatika.model.LuasSegitiga
import java.math.BigInteger

class MainVIewModel : ViewModel() {
    private val luasPersegi = MutableLiveData<LuasPersegi?>()
    private val luasPersegiPanjang = MutableLiveData<LuasPersegiPanjang?>()
    private val luasSegitiga = MutableLiveData<LuasSegitiga?>()

    fun countPersegi(sisi: BigInteger){
        val hasil = sisi*sisi
        luasPersegi.value = LuasPersegi(hasil)
    }
    fun countPersegiPanjang(panjang: BigInteger, lebar: BigInteger){
        val hasil = panjang*lebar
        luasPersegiPanjang.value = LuasPersegiPanjang(hasil)
    }
    fun countSegitiga(alas: Float, tinggi: Float){
        val hasil = alas*tinggi
        luasSegitiga.value = LuasSegitiga(hasil)
    }

    fun getLuasPersegi(): LiveData<LuasPersegi?> = luasPersegi
    fun getLuasPersegiPanjang(): LiveData<LuasPersegiPanjang?> = luasPersegiPanjang
    fun getLuasSegitiga(): LiveData<LuasSegitiga?> = luasSegitiga

}