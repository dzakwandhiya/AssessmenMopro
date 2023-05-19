package com.mopro.rumusdasarmatermatika.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hasil")
data class HasilEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var bangun: String,
    var input: String,
    var hasil_rumus: Float
)
