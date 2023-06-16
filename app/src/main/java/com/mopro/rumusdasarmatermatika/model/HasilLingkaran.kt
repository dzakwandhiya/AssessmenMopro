package com.mopro.rumusdasarmatermatika.model

import com.mopro.rumusdasarmatermatika.db.HasilEntity

fun HasilEntity.hitungLingkaran(): LuasLingkaran{
    val hasil = hasil_rumus
    return LuasLingkaran(hasil)
}