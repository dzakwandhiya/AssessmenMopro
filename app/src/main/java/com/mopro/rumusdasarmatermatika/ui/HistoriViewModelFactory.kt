package com.mopro.rumusdasarmatermatika.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mopro.rumusdasarmatermatika.db.HasilDao

class HistoriViewModelFactory(private val db: HasilDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun<T : ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(HistoriViewModel::class.java)){
            return HistoriViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}