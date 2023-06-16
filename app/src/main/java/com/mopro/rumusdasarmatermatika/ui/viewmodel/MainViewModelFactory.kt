package com.mopro.rumusdasarmatermatika.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mopro.rumusdasarmatermatika.db.HasilDao

class MainViewModelFactory(private val db: HasilDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun<T : ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}