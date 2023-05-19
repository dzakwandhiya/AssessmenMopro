package com.mopro.rumusdasarmatermatika.model

import com.mopro.rumusdasarmatermatika.db.HasilEntity

fun HasilEntity.hitungSegitiga(): LuasSegitiga{
    val hasil = hasil_rumus
    return LuasSegitiga(hasil)
}