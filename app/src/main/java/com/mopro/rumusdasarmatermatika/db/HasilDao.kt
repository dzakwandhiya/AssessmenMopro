package com.mopro.rumusdasarmatermatika.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HasilDao {
    @Insert
    fun insert(hasil: HasilEntity)
    @Query("SELECT * FROM hasil ORDER BY id DESC")
    fun getLastHasil(): LiveData<List<HasilEntity>>
    @Query("DELETE FROM hasil")
    fun clearData()
}