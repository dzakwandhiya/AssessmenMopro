package com.mopro.rumusdasarmatermatika.model

import com.mopro.rumusdasarmatermatika.db.HasilEntity

fun HasilEntity.hitungPersegi(): LuasPersegi{
    val hasil = hasil_rumus
    return LuasPersegi(hasil)
}