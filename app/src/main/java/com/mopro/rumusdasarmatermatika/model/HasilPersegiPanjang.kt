package com.mopro.rumusdasarmatermatika.model

import com.mopro.rumusdasarmatermatika.db.HasilEntity

fun HasilEntity.hitungPersegiPanjang(): LuasPersegiPanjang{
    val hasil = hasil_rumus
    return LuasPersegiPanjang(hasil)
}