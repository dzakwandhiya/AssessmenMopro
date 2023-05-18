package com.mopro.rumusdasarmatermatika.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopro.rumusdasarmatermatika.db.HasilDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoriViewModel(private val db: HasilDao) : ViewModel(){
    val data = db.getLastHasil()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}